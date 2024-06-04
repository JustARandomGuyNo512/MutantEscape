package mutantescape.level.event;


import mutantescape.level.event.function.EntityDeathSpread;
import mutantescape.level.register.entity.goal.BlockBreakerGoal;
import mutantescape.level.register.entity.goal.NearestAttackTargetGoal;
import mutantescape.tools.Utils.MCUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class LivingEventBus {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    static class CommonEvent{
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onSpawn(EntityJoinLevelEvent event) {
            if (event.getEntity() instanceof Zombie Mob) {
                Mob.goalSelector.addGoal(1,new BlockBreakerGoal(Mob,0,1.25d,false,false,false));
                Mob.goalSelector.addGoal(8,new NearestAttackTargetGoal<>(Mob, Player.class, false,32,32));
                Mob.goalSelector.addGoal(9,new NearestAttackTargetGoal<>(Mob, LivingEntity.class, false,32,32));
            }else if (event.getEntity() instanceof Player player){
                MCUtils.setAttributeValue(player, Attributes.MAX_HEALTH, 60);
            }

        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    static class LivingEvnt{
        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event) {
            Entity EntitySource = event.getSource().getEntity();
            if (event.getEntity().level() instanceof ServerLevel serverLevel && EntitySource!=null) {
                EntityDeathSpread.SpreadSpawn(event.getEntity(), EntitySource, serverLevel,false);
            }
        }


        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent event) {

        }

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {

        }


        @SubscribeEvent
        public static void onSaplingGrowTree(SaplingGrowTreeEvent event) {

        }










    }




}

