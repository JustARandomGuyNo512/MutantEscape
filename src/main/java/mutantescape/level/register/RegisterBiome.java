package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.Particle.ScapeBiomeParticle;
import mutantescape.tools.ModSet;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterBiome {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MutantEscape.MODID);
    public static final RegistryObject<Biome> SPACE_BIOMES = BIOMES.register(ModSet.ModKey("infected_area"), () -> new Biome.BiomeBuilder()
            .temperature(0)
            .downfall(0)
            .hasPrecipitation(false)
            .generationSettings(BiomeGenerationSettings.EMPTY)
            .mobSpawnSettings(new MobSpawnSettings
                    .Builder()
                    .build())
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .ambientParticle(new AmbientParticleSettings(
                            new ScapeBiomeParticle(),0.05f
                    ))
                    .fogColor(12638463)
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .skyColor(7972607)
                    .grassColorOverride(9470285)
                    .foliageColorOverride(10387789)
                    .build())
            .build());























}
