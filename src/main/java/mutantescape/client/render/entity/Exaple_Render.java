package mutantescape.client.render.entity;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Exple_entity;
import mutantescape.client.render.entity.model.IEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static mutantescape.client.render.entity.model.IEntityModel.*;

public class Exaple_Render extends MobRenderer<Exple_entity, IEntityModel<Exple_entity>> {
    private static final ResourceLocation TEXTURES_LOCATION = new ResourceLocation(MutantEscape.MODID,"textures/entity/texture.png");



    public Exaple_Render(EntityRendererProvider.Context context) {

        super(context, new IEntityModel<>(context.bakeLayer(EXAMPLE_ENTITY)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Exple_entity pEntity) {
        return TEXTURES_LOCATION;
    }



}
