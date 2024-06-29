package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Zombie_Entity;
import mutantescape.level.register.entity.villager_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Villager_Model extends GeoModel<villager_Entity> {

    @Override
    public ResourceLocation getModelResource(villager_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_villager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(villager_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_villager.png");
    }

    @Override
    public ResourceLocation getAnimationResource(villager_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_villager.animation.json");
    }
}
