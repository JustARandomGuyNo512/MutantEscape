package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Iron_Golem_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Iron_Golem_Model extends GeoModel<Iron_Golem_Entity> {

    @Override
    public ResourceLocation getModelResource(Iron_Golem_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape__iron_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Iron_Golem_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_iron_golem.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Iron_Golem_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_iron_golem.animation.json");
    }
}
