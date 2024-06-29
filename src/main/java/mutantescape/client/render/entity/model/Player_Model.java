package mutantescape.client.render.entity.model;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.Player_Entity;
import mutantescape.level.register.entity.Zombie_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Player_Model extends GeoModel<Player_Entity> {

    @Override
    public ResourceLocation getModelResource(Player_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "geo/mutantescape_player.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Player_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID,"textures/entities/mutantescape_player.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Player_Entity sheepEntity) {
        return new ResourceLocation(MutantEscape.MODID, "animations/mutantescape_player_model.animation.json");
    }
}
