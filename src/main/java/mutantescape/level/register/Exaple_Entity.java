package mutantescape.level.register;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class Exaple_Entity extends Monster {
    protected Exaple_Entity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Exaple_Entity(PlayMessages.SpawnEntity packet, Level world) {
        this(EntityRegister.EXPALE_ENTITY.get(), world);
    }
}
