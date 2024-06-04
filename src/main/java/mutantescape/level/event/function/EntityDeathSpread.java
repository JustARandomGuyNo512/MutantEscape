package mutantescape.level.event.function;

import mutantescape.level.capability.CapabilityProvider;
import mutantescape.level.register.RegisterEntity;
import mutantescape.level.register.entity.Exple_entity;
import mutantescape.tools.UtilsModType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Objects;

public class EntityDeathSpread {
    private static HashMap<EntityType<?>, EntityType<?>> AEntity=new HashMap<>();
    static {
      AEntity.put(EntityType.ZOMBIE, RegisterEntity.Master_Catalog.get());
    }




    public static void SpreadSpawn(LivingEntity livingEntity, Entity Source, ServerLevel level,boolean isModTypeMatching){
           if (AEntity.containsKey(Source.getType())){
               if (isModTypeMatching && livingEntity.getMobType() == UtilsModType.MUTANESCAPE){
                   SpawnEgg(livingEntity,Source,level);
               }else {
                   SpawnEgg(livingEntity,Source,level);
               }
           }

    }

    public static void SpawnEgg(LivingEntity livingEntity, Entity Source, ServerLevel level){
        EntityType<?> EntityTypeSpread = AEntity.get(Source.getType());
        EntityTypeSpread.spawn(level,new BlockPos(livingEntity.getBlockX(),livingEntity.getBlockY(),livingEntity.getBlockZ()), MobSpawnType.EVENT);
        level.sendParticles(ParticleTypes.EXPLOSION,livingEntity.getBlockX(),livingEntity.getBlockY()+1,livingEntity.getBlockZ(),1,0,0,0,1.5f);
        livingEntity.remove(Entity.RemovalReason.DISCARDED);
        if (Minecraft.getInstance().player !=null){
            Minecraft.getInstance().player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                capability.setAttrValue("evolution_stage_value",capability.getAttrValue("evolution_stage_value")+0.5);
            }));
        }
    }
}
