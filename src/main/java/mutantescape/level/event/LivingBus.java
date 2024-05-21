package mutantescape.level.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingBus {
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
