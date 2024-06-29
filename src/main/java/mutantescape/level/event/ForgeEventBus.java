package mutantescape.level.event;

import mutantescape.level.function.BiomeFunction;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBus {
   public static BiomeFunction Biome;
    @SubscribeEvent
    public static void onTickLevelTick(TickEvent.LevelTickEvent event) {

    }





    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        Biome=new BiomeFunction();
    }


    @SubscribeEvent
    public static void onTickServerTick(TickEvent.ServerTickEvent event) {
        if (event.getServer().overworld().getRandom().nextDouble() >0.5d) {
            BiomeFunction.Time++;
            if (event.phase == TickEvent.Phase.END &&  BiomeFunction.Time>BiomeFunction.MaxTime) {
                BiomeFunction.ScapeBlockPos();
                BiomeFunction.Time=0;


            }
        }



    }
}
