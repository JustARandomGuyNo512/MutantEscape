package mutantescape.level.register;

import mutantescape.MutantEscape;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Exaple_Render extends MobRenderer<Exple_entity, IEntityModel<Exple_entity>> {//HumanoidModel 表示模型这里用了原版的人形生物代替了
    private static final ResourceLocation TEXTURES_LOCATION = new ResourceLocation(MutantEscape.MODID,"textures/entity/texture.png");

    public static final ModelLayerLocation EXAMPLE_ENTITY = new ModelLayerLocation(new ResourceLocation(MutantEscape.MODID, "example_entity"), "main");


    public Exaple_Render(EntityRendererProvider.Context context) {
        super(context, new IEntityModel<>(context.bakeLayer(EXAMPLE_ENTITY)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Exple_entity pEntity) {
        return TEXTURES_LOCATION;
    }

}
