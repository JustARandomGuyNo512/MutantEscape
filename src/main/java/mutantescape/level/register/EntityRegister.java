package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.IkeySet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITYS= DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MutantEscape.MODID);
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITYS.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }

    public static final RegistryObject<EntityType<Exple_entity>> Master_Catalog=register(IkeySet.ModKey("Master_Catalog")
            , EntityType.Builder.<Exple_entity>of(Exple_entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Exple_entity::new)
                    .sized(0.6f, 1.8f));




}
