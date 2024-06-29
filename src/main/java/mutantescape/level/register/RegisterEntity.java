package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.entity.*;
import mutantescape.tools.ModSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterEntity {
    public static final DeferredRegister<EntityType<?>> ENTITYS= DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MutantEscape.MODID);
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITYS.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }

    public static final RegistryObject<EntityType<Sheep_Entity>> Sheep_Entity=register(ModSet.ModKey("Sheep_Entity")
            , EntityType.Builder.<Sheep_Entity>of(Sheep_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Sheep_Entity::new)
                    .sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<Cow_Entity>> Cow_Entity=register(ModSet.ModKey("Cow_Entity")
            , EntityType.Builder.<Cow_Entity>of(Cow_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Cow_Entity::new)
                    .sized(0.6f, 1.8f));
    public static final RegistryObject<EntityType<Pig_Entity>> PIG_Entity=register(ModSet.ModKey("PIG_Entity")
            , EntityType.Builder.<Pig_Entity>of(Pig_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Pig_Entity::new)
                    .sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<Zombie_Entity>> ZOMBIE_Entity=register(ModSet.ModKey("ZOMBIE_Entity")
            , EntityType.Builder.<Zombie_Entity>of(Zombie_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Zombie_Entity::new)
                    .sized(0.6f, 1.8f));
    public static final RegistryObject<EntityType<Iron_Golem_Entity>> IRON_GOLEM_Entity=register(ModSet.ModKey("GOLEM_Entity")
            , EntityType.Builder.<Iron_Golem_Entity>of(Iron_Golem_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Iron_Golem_Entity::new)
                    .sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<villager_Entity>> Villager_Entity=register(ModSet.ModKey("Villager_Entity")
            , EntityType.Builder.<villager_Entity>of(villager_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(villager_Entity::new)
                    .sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<Spider_Entity>> Spider_Entity=register(ModSet.ModKey("Spider_Entity")
            , EntityType.Builder.<Spider_Entity>of(Spider_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Spider_Entity::new)
                    .sized(2.5f, 2.0f));
    public static final RegistryObject<EntityType<Player_Entity>> Player_Entity=register(ModSet.ModKey("Player_Entity")
            , EntityType.Builder.<Player_Entity>of(Player_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Player_Entity::new)
                    .sized(0.6f, 2.3f));

    public static final RegistryObject<EntityType<Catalog_Entity>> Catalog_Entity=register(ModSet.ModKey("Catalog_Entity")
            , EntityType.Builder.<Catalog_Entity>of(Catalog_Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Catalog_Entity::new)
                    .sized(0.6f, 2.3f));










}
