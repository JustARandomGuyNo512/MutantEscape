package mutantescape.level.register;

import mutantescape.MutantEscape;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Me_Entity {
    public static final DeferredRegister<EntityType<?>> ENTITYS=DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MutantEscape.MODID);
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITYS.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }






}
