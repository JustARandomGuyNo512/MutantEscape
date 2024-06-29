package mutantescape.client.render.overlay;

import mutantescape.level.capability.CapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.Objects;


@OnlyIn(Dist.CLIENT)
public class Stage {
    public static final IGuiOverlay OVERLAY = Stage::render;
    public static boolean complete;

    private static void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        Minecraft minecraft = Minecraft.getInstance();
        if (complete && !minecraft.options.renderDebug) {
            if (minecraft.player != null) {
                    minecraft.player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                        guiGraphics.drawString(minecraft.font,String.valueOf(capability.getAttrValue("evolution_stage_value")),0,0, Objects.requireNonNull(TextColor.parseColor("#ffffff")).getValue());
                    }));
}
        }
    }
}
