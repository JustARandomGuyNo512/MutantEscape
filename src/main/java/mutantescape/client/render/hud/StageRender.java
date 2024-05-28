package mutantescape.client.render.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class StageRender {
    public static final IGuiOverlay OVERLAY = StageRender::render;
    public static boolean complete;
    private static void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        Minecraft minecraft = Minecraft.getInstance();
        if (complete && !minecraft.options.renderDebug) {
            guiGraphics.drawString(minecraft.font, Component.literal("测试"),0,0, TextColor.parseColor("#ffffff").getValue());





        }
    }
}
