package mutantescape.level.register.Particle;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mutantescape.tools.ModSet;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.awt.*;


public class TextParticle extends Particle {
    private final String text;
    private final SpriteSet spriteSet;
    private final float gravity = -0.03f;
    private int lifetime;



    public TextParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet, String text) {
        super(pLevel,pX,pY,pZ,pXSpeed,pYSpeed,pZSpeed);
        this.spriteSet=spriteSet;
        this.text = text;
        this.lifetime = 20;

    }

    @Override
    public void tick() {
        super.tick();
        this.yd -= gravity;



        if (this.lifetime <= 0) {
            this.remove(); // 移除粒子

        }
    }

    @Override
    public void render(@NotNull VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        PoseStack poseStack = new PoseStack();
        Vec3 vec3 = pRenderInfo.getPosition();
        float x = (float)(Mth.lerp(pPartialTicks, this.xo, this.x) - vec3.x());
        float y = (float)(Mth.lerp(pPartialTicks, this.yo, this.y) - vec3.y());
        float z = (float)(Mth.lerp(pPartialTicks, this.zo, this.z) - vec3.z());



        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(-0.025F, -0.025F, 0.025F);

        poseStack.mulPose(pRenderInfo.rotation());
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        Matrix4f matrix = poseStack.last().pose();
        Minecraft.getInstance().font.drawInBatch(Component.literal(this.text), 0, 0, 0xFFFFFF, false, matrix, bufferSource, Font.DisplayMode.NORMAL,0, 15728880);
        bufferSource.endBatch();
        poseStack.popPose();
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }











}
