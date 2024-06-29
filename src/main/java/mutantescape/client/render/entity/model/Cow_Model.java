package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Cow_Entity;
import mutantescape.level.register.entity.Sheep_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Cow_Model extends GeoModel<Cow_Entity> {

    @Override
    public ResourceLocation getModelResource(Cow_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_cow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cow_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_cow.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cow_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_cow_model.animation.json");
    }
}
