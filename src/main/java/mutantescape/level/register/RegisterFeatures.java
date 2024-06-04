package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.tree.TreeFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MutantEscape.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> EXPALE_TREE = FEATURES.register("exaple_tree",
            () -> new TreeFeature(NoneFeatureConfiguration.CODEC,new ResourceLocation(MutantEscape.MODID,"")));



}
