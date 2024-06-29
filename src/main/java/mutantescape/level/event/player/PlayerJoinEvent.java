package mutantescape.level.event.player;

import mutantescape.level.capability.CapabilityProvider;
import mutantescape.tools.RegexTest;
import mutantescape.tools.Utils.MCUtils;
import mutantescape.tools.font.ComponentA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerJoinEvent {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().sendSystemMessage(ComponentA.translatable(event.getEntity(),"mutantescape.log.1"));

    }
}
