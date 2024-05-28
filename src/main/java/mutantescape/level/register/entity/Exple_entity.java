package mutantescape.level.register.entity;

import mutantescape.level.register.RegisterEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class Exple_entity extends Monster {
    public Exple_entity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(RegisterEntity.Master_Catalog.get(),level);
    }

    public Exple_entity(EntityType<Exple_entity> expleEntityEntityType, Level level) {
        super(expleEntityEntityType,level);

    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 25);
        builder = builder.add(Attributes.ARMOR, 1.2);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 4.0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        return builder;
    }


}
