package mutantescape.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mutantescape.client.render.entity.model.Player_Model;
import mutantescape.client.render.entity.model.Zombie_Model;
import mutantescape.level.register.entity.Player_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Player_Render extends GeoEntityRenderer<Player_Entity> {
    public Player_Render(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Player_Model());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(Player_Entity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, Player_Entity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float scale = 1f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    protected float getDeathMaxRotation(Player_Entity entityLivingBaseIn) {
        return 0.0F;
    }



}
