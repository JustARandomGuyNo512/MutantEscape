package mutantescape.level.event.player;

import mutantescape.tools.ModSet;
import mutantescape.tools.RegexTest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.TitleCommand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerJoinEvent {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().sendSystemMessage(RegexTest.GetText (event.getEntity(), "<#Color:337788>欢迎您:</Color> <#Color:00FF7F> <player> </Color> ，<#Color:221760>祝您玩的愉快! </Color>"));
    }
}
