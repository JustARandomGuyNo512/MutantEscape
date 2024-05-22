package mutantescape.client.render.sky;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class OverEffect extends DimensionSpecialEffects {
    public OverEffect(float pCloudLevel, boolean pHasGround, SkyType pSkyType, boolean pForceBrightLightmap, boolean pConstantAmbientLight) {
        super(pCloudLevel, pHasGround, pSkyType, pForceBrightLightmap, pConstantAmbientLight);
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {





        return super.renderSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 pFogColor, float pBrightness) {
        return null;
    }

    @Override
    public boolean isFoggyAt(int pX, int pY) {
        return false;
    }
}
