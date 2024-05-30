package mutantescape.level.event;

import mutantescape.level.capability.MECapabilityProvider;
import mutantescape.tools.BiomeUtils;
import mutantescape.tools.ModSet;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DebugEvents {

    @SubscribeEvent
    public static void renderEntityName(RenderNameTagEvent event) {
        if (event.getEntity() instanceof Player player) {
            event.setResult(Event.Result.ALLOW);
            Component component = event.getContent();
            player.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {

            }));
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onButtonPress(InputEvent.Key event) {
        if (event.getAction() == 1) {
            Player player = Minecraft.getInstance().player;
            if (player == null) {
                return;
            }
            if (event.getKey() == 321) {
                if (player.getServer()!=null && player.getServer().overworld().getPlayerByUUID(player.getUUID())!=null) {
                    BiomeUtils.setBiome(player.getServer().overworld().getLevel(),player.getServer().overworld().getPlayerByUUID(player.getUUID()).getOnPos(), Biomes.BASALT_DELTAS);
                }
                player.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                   capability.setAttrValue("test_broadcast", capability.getAttrValue("test_broadcast") + 1);
                }));
            }
            if (event.getKey() == 322) {
                player.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    capability.setAttrValue("test_onlyC2S", capability.getAttrValue("test_onlyC2S") + 1);
                }));
            }
        }
    }
}
