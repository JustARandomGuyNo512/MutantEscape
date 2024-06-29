package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Sheep_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Sheep_Model extends GeoModel<Sheep_Entity> {

    @Override
    public ResourceLocation getModelResource(Sheep_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_sheep.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sheep_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_sheep.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sheep_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_sheep.animation.json");
    }
}
