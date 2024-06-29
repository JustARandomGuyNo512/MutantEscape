package mutantescape.level.event;


import mutantescape.level.function.EntityDeathSpread;
import mutantescape.level.register.Particle.ParticleType.DamageParticleData;
import mutantescape.level.register.RegisterEffect;
import mutantescape.level.register.entity.goal.BlockBreakerGoal;
import mutantescape.level.register.entity.goal.NearestAttackTargetGoal;
import mutantescape.tools.ModSet;
import mutantescape.tools.UtilsModType;
import mutantescape.tools.font.ComponentA;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class LivingEventBus {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    static class CommonEvent{
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onSpawn(EntityJoinLevelEvent event) {
            //初始化数值修改或添加
            if (event.getEntity() instanceof Zombie Mob) {
                //为僵尸添加挖掘方块AI与透视墙壁AI
                Mob.goalSelector.addGoal(1,new BlockBreakerGoal(Mob,0,1.25d,false,false,false));
                Mob.goalSelector.addGoal(8,new NearestAttackTargetGoal<>(Mob, Player.class, false,32,32));
                Mob.goalSelector.addGoal(9,new NearestAttackTargetGoal<>(Mob, LivingEntity.class, false,32,32));
            }else if (event.getEntity() instanceof Player player){
                //MCUtils.setAttributeValue(player, Attributes.MAX_HEALTH,40);
            }






        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    static class LivingEvnt{
        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event) {
            Entity EntitySource = event.getSource().getEntity();
            //处理生物感染其他生物
            if (event.getEntity().level() instanceof ServerLevel serverLevel && EntitySource!=null) {
                EntityDeathSpread.SpreadSpawn(event.getEntity(), EntitySource, serverLevel);
            }
            //处理虫灵感染其他生物
            if (event.getEntity().getEffect(RegisterEffect.MUTANT_ESCAPE_EFFECT.get())!=null && !(event.getEntity() instanceof Player)) {
                EntityDeathSpread.SpawnEggA2(event.getEntity(), (ServerLevel) event.getEntity().level());
            }
        }




        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity.level().isClientSide){
                return;
            }

            double x = entity.getX();
            double y = entity.getY() + entity.getBbHeight() / 2.0;
            double z = entity.getZ();
            String damageText = String.valueOf((int) event.getAmount());
            if (entity.level() instanceof ServerLevel serverLevel && event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity.getMobType() == UtilsModType.MUTANESCAPE) {
                    //创建伤害数字粒子与添加感染效果
                    serverLevel.sendParticles(new DamageParticleData(damageText), x, y, z, 1, 0.0, 0.0, 0.0, 0.5);
                }
            }
        }

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {
            //处理效果到时间后转换生物
            MobEffectInstance AEffect = event.getEntity().getEffect(RegisterEffect.MUTANT_ESCAPE_EFFECT.get());
           if (AEffect!=null) {
               int tick = AEffect.getDuration() / 20;
               if (event.getEntity() instanceof Mob && tick<=1) {
                       EntityDeathSpread.SpawnEggA2( event.getEntity(), (ServerLevel) event.getEntity().level());
               }
           }
        }


        @SubscribeEvent
        public static void onSaplingGrowTree(SaplingGrowTreeEvent event) {

        }




        @SubscribeEvent
        public static void onMobSpawn(MobSpawnEvent event) {
            if (event.getLevel() instanceof ServerLevel && event.getEntity() instanceof Zombie){

            }



        }



    }




}

