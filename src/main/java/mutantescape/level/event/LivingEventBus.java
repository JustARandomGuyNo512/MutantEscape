package mutantescape.level.event;


import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class LivingEventBus {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    class CommonEvent{
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onSpawn(EntityJoinLevelEvent event) {
            if (event.getEntity() instanceof Zombie Mob) {

            }

        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    class LivingEvnt{
        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event) {

        }


        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent event) {

        }

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {

        }
    }




}

