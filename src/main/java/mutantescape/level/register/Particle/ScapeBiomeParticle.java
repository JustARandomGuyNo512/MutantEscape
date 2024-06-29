package mutantescape.level.register.Particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mutantescape.level.register.RegisterParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ScapeBiomeParticle implements ParticleOptions{


    public ScapeBiomeParticle() {

    }


    @Override
    public @NotNull ParticleType<?> getType() {
        return RegisterParticleTypes.BIOME_SCRAPE.get();
    }

    @Override
    public void writeToNetwork(@NotNull FriendlyByteBuf pBuffer) {

    }

    @Override
    public @NotNull String writeToString() {
        ResourceLocation registryName = ForgeRegistries.PARTICLE_TYPES.getKey(this.getType());
        if (registryName == null) {
            throw new IllegalStateException("Unregistered particle type: " + this.getType());
        }
        return registryName.toString();
    }


}
