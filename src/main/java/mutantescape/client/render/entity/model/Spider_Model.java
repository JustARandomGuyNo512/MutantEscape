package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Spider_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Spider_Model extends GeoModel<Spider_Entity> {

    @Override
    public ResourceLocation getModelResource(Spider_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_spider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Spider_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_spider.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Spider_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_spider_model.animation.json");
    }
}
