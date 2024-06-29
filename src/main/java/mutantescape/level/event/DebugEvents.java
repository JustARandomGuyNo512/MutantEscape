package mutantescape.level.event;

import mutantescape.level.capability.CapabilityProvider;
import mutantescape.level.register.entity.Player_Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DebugEvents {

    @SubscribeEvent
    public static void renderEntityName(RenderNameTagEvent event) {
        if (event.getEntity() instanceof Player_Entity) {
            event.setResult(Event.Result.ALLOW);
        }
        if (event.getEntity() instanceof Player player) {
            event.setResult(Event.Result.ALLOW);
            Component component = event.getContent();
            player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {

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

            if (event.getKey()==299){


            }




            if (event.getKey() == 322) {
                player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    capability.setAttrValue("test_onlyC2S", capability.getAttrValue("test_onlyC2S") + 1);
                }));
            }
        }
    }
}
