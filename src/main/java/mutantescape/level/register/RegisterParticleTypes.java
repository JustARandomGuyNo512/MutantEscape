package mutantescape.level.register;

import com.mojang.serialization.Codec;
import mutantescape.MutantEscape;
import mutantescape.level.register.Particle.ParticleType.DamageParticleData;
import mutantescape.tools.ModSet;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class RegisterParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MutantEscape.MODID);
    public static final RegistryObject<ParticleType<DamageParticleData>> DamageNorber = REGISTRY.register(ModSet.ModKey("blood"), ()->new ParticleType<DamageParticleData>(false, DamageParticleData.DESERIALIZER) {
        @Override
        public @NotNull Codec<DamageParticleData> codec() {
            return DamageParticleData.CODEC;
        }
    });
    public static final RegistryObject<SimpleParticleType> BIOME_SCRAPE = REGISTRY.register(ModSet.ModKey("dust_of_infection"), () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BLOOD_1 = REGISTRY.register(ModSet.ModKey("blood_1insect"), () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BLOOD_2 = REGISTRY.register(ModSet.ModKey("blood_1"), () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BLOOD_3 = REGISTRY.register(ModSet.ModKey("blood_1waterinsect"), () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BLOOD_4 = REGISTRY.register(ModSet.ModKey("blood_1water"), () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BLOOD_5 = REGISTRY.register(ModSet.ModKey("blood_2insect"), () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BLOOD_6 = REGISTRY.register(ModSet.ModKey("blood_2"), () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BLOOD = REGISTRY.register("blood", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD_SPLAT = REGISTRY.register("blood_splat", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD_FOG = REGISTRY.register("blood_fog", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD_SPLASH = REGISTRY.register("blood_splash", () -> new SimpleParticleType(true));




}
