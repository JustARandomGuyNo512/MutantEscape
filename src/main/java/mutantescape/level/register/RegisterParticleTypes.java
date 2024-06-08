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



}
