package mutantescape.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mutantescape.client.render.entity.model.Spider_Model;
import mutantescape.client.render.entity.model.Zombie_Model;
import mutantescape.level.register.entity.Spider_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Spider_Render extends GeoEntityRenderer<Spider_Entity> {
    public Spider_Render(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Spider_Model());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(Spider_Entity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, Spider_Entity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float scale = 1f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    protected float getDeathMaxRotation(Spider_Entity entityLivingBaseIn) {
        return 0.0F;
    }



}
