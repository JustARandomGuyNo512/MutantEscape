package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Cow_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Zombie_Model extends GeoModel<Zombie_Entity> {

    @Override
    public ResourceLocation getModelResource(Zombie_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_zombie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Zombie_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_zombie.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Zombie_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_zombie_model.animation.json");
    }
}
