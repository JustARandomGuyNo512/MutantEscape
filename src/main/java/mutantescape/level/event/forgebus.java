package mutantescape.level.event;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class forgebus {
    @SubscribeEvent
    public static void onTickLevelTick(TickEvent.LevelTickEvent event) {

    }

}
