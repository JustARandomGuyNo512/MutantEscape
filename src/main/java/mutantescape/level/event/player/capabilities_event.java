package mutantescape.level.event.player;

import mutantescape.MutantEscape;
import mutantescape.level.capability.MEAttrubes;
import mutantescape.level.capability.MECapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MutantEscape.MODID)
public class capabilities_event {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer) {
            if (!event.getObject().getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).isPresent()) {
                event.addCapability(new ResourceLocation(MutantEscape.MODID, "profession"), new MECapabilityProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(MEAttrubes.class);
    }

    @SubscribeEvent
    public static void endFix(PlayerEvent.Clone event)
    {
        if (!event.isWasDeath() && !event.getEntity().level().isClientSide)//切换维度
        {
            Player oldPlayer = event.getOriginal();
            oldPlayer.reviveCaps();
            event.getEntity().getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap ->
                    oldPlayer.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap::copy));

            oldPlayer.invalidateCaps();
        }else if (!event.getEntity().level().isClientSide){//死亡
            Player oldPlayer = event.getOriginal();
            oldPlayer.reviveCaps();

            event.getEntity().getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap ->
                    oldPlayer.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent(cap::copy));
            oldPlayer.invalidateCaps();

        }
    }



















}
