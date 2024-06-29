package mutantescape.level.function;

import mutantescape.level.capability.CapabilityProvider;
import mutantescape.level.network.UpdateDataCient.Clients;
import mutantescape.level.register.RegisterEntity;
import mutantescape.tools.ModSet;
import mutantescape.tools.UtilsModType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

import java.util.HashMap;
import java.util.Objects;

public class EntityDeathSpread {
    static class ScapeCore{
        public ScapeCore(EntityType<?> entityType, int level) {
            this.entityType = entityType;
            this.level = level;
        }

        public EntityType<?> getEntityType() {
            return entityType;
        }

        public int getLevel() {
            return level;
        }

        EntityType<?> entityType;
        int level;
    }


    private static final HashMap<EntityType<?>, ScapeCore> AEntity=new HashMap<>();
    static {
      AEntity.put(EntityType.SHEEP, new ScapeCore(RegisterEntity.Sheep_Entity.get(),1));
      AEntity.put(EntityType.COW, new ScapeCore(RegisterEntity.Cow_Entity.get(),1));
      AEntity.put(EntityType.PIG,  new ScapeCore(RegisterEntity.PIG_Entity.get(),1));
      AEntity.put(EntityType.ZOMBIE,  new ScapeCore(RegisterEntity.ZOMBIE_Entity.get(),1));
      AEntity.put(EntityType.IRON_GOLEM, new ScapeCore(RegisterEntity.IRON_GOLEM_Entity.get(),1));
      AEntity.put(EntityType.VILLAGER, new ScapeCore(RegisterEntity.Villager_Entity.get(),1));
      AEntity.put(EntityType.SPIDER, new ScapeCore(RegisterEntity.Spider_Entity.get(),1));
      AEntity.put(EntityType.PLAYER, new ScapeCore(RegisterEntity.Player_Entity.get(),1));

    }

    public static Entity SpreadSpawn(LivingEntity livingEntity, Entity Source, ServerLevel level){
           if (AEntity.containsKey(livingEntity.getType())){
               if (Source instanceof LivingEntity livingEntity1 && livingEntity1.getMobType() ==UtilsModType.MUTANESCAPE){
                  return SpawnEgg(livingEntity1,livingEntity,level);
               }
           }
        return Source;
    }

    public static Entity SpawnEgg(LivingEntity livingEntity, Entity Source, ServerLevel level){
        ScapeCore EntityTypeSpread = AEntity.get(Source.getType());
        if (EntityTypeSpread!=null) {
            Source.remove(Entity.RemovalReason.DISCARDED);
            Objects.requireNonNull(EntityTypeSpread.getEntityType().spawn(level, new BlockPos(livingEntity.getBlockX(), livingEntity.getBlockY(), livingEntity.getBlockZ()), MobSpawnType.EVENT)).setCustomName(livingEntity.getCustomName());
            level.sendParticles(ParticleTypes.EXPLOSION, livingEntity.getBlockX(), livingEntity.getBlockY() + 1, livingEntity.getBlockZ(), 1, 0, 0, 0, 1.5f);
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    capability.setAttrValue("evolution_stage_value", capability.getAttrValue("evolution_stage_value") + 0.5);
                }));


            }

        }
        return Source;
    }


    public static void SpawnEggA2(LivingEntity livingEntity, ServerLevel level) {
        ScapeCore EntityTypeSpread = AEntity.get(livingEntity.getType());
        if (EntityTypeSpread != null) {
            livingEntity.remove(Entity.RemovalReason.DISCARDED);
            Objects.requireNonNull( EntityTypeSpread.getEntityType().spawn(level, new BlockPos(livingEntity.getBlockX(), livingEntity.getBlockY(), livingEntity.getBlockZ()), MobSpawnType.EVENT)).setCustomName(livingEntity.getCustomName());
            level.sendParticles(ParticleTypes.EXPLOSION, livingEntity.getBlockX(), livingEntity.getBlockY() + 1, livingEntity.getBlockZ(), 1, 0, 0, 0, 1.5f);
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    capability.setAttrValue("evolution_stage_value", capability.getAttrValue("evolution_stage_value") + 0.5);
                }));
            }

        }

    }





}
