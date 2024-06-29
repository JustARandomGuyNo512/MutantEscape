package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Cow_Entity;
import mutantescape.level.register.entity.Pig_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Pig_Model extends GeoModel<Pig_Entity> {

    @Override
    public ResourceLocation getModelResource(Pig_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_pig.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Pig_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_pig.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Pig_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_pig_model.animation.json");
    }
}
