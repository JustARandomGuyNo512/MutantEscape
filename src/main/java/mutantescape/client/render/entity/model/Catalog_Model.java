package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Catalog_Entity;
import mutantescape.level.register.entity.Cow_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Catalog_Model extends GeoModel<Catalog_Entity> {

    @Override
    public ResourceLocation getModelResource(Catalog_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_catalog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Catalog_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_catalog.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Catalog_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_catalog.model.animation.json");
    }
}
