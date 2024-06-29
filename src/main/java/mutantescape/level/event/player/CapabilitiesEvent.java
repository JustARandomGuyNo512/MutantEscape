package mutantescape.level.event.player;

import mutantescape.MutantEscape;
import mutantescape.level.capability.Attribute;
import mutantescape.level.capability.CapabilityProvider;
import mutantescape.level.network.PacketHandler;
import mutantescape.level.network.c2s.SyncAttributesToServerPacket;
import mutantescape.level.network.s2c.BroadcastAttributesToClientPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = MutantEscape.MODID)
public class CapabilitiesEvent {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).isPresent()) {
                event.addCapability(new ResourceLocation(MutantEscape.MODID, "profession"), new CapabilityProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CapabilityProvider.class);
    }

    @SubscribeEvent
    public static void endFix(PlayerEvent.Clone event)
    {
        if (!event.isWasDeath() && !event.getEntity().level().isClientSide)//切换维度
        {
            Player oldPlayer = event.getOriginal();
            oldPlayer.reviveCaps();
            event.getEntity().getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap ->
                    oldPlayer.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap::copy));

            oldPlayer.invalidateCaps();
        }else if (!event.getEntity().level().isClientSide){//死亡
            Player oldPlayer = event.getOriginal();
            oldPlayer.reviveCaps();

            event.getEntity().getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap ->
                    oldPlayer.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap::copy));
            oldPlayer.invalidateCaps();

        }
    }


    @SubscribeEvent
    public static void playerServerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (player.level().isClientSide()) {
                //客户端检测是否向服务端发送同步
                player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    if (capability.isNeedSync()) {
                        //send to server
                        List<Attribute> needSyncList = capability.getProfession().values().stream()
                                .filter(Attribute::isNeedSync)
                                .peek(obj -> obj.setNeedSync(false))
                                .collect(Collectors.toCollection(ArrayList::new));
                        PacketHandler.simpleChannel.sendToServer(new SyncAttributesToServerPacket(needSyncList));
                        capability.setNeedSync(false);
                    }
                }));

            } else {
                //服务端检测是否要向其它玩家广播同步
                player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    if (capability.isNeedSync()) {
                        //收集需要广播的属性
                        List<Attribute> needSyncList = capability.getProfession().values().stream()
                                .filter(attribute -> attribute.isNeedSync() && !attribute.isOnlyC2S())
                                .peek(obj -> obj.setNeedSync(false))
                                .collect(Collectors.toCollection(ArrayList::new));
                        PacketHandler.simpleChannel.send(PacketDistributor.TRACKING_ENTITY.with(() -> event.player), new BroadcastAttributesToClientPacket(needSyncList, player.getId()));
                        capability.setNeedSync(false);
                    }
                }));
            }
        }
    }
















}
