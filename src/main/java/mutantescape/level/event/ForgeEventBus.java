package mutantescape.level.event;

import mutantescape.level.register.RegisterFeatures;
import mutantescape.tools.ModSet;
import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBus {
    @SubscribeEvent
    public static void onTickLevelTick(TickEvent.LevelTickEvent event) {





    }
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        BiomeUtils.ReqBiome(event.getServer(),new ResourceLocation("minecraft", "cherry"));

    }

}
