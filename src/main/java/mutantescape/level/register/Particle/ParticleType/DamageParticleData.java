package mutantescape.level.register.Particle.ParticleType;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mutantescape.level.register.RegisterParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public class DamageParticleData implements ParticleOptions {

    public static final Codec<DamageParticleData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("text").forGetter(data -> data.text)
    ).apply(instance, DamageParticleData::new));
    @Deprecated
    public static final ParticleOptions.Deserializer<DamageParticleData> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public @NotNull DamageParticleData fromCommand(@NotNull ParticleType<DamageParticleData> particleType, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            String text = reader.readString();
            return new DamageParticleData(text);
        }

        @Override
        public @NotNull DamageParticleData fromNetwork(@NotNull ParticleType<DamageParticleData> particleType, FriendlyByteBuf buffer) {
            return new DamageParticleData(buffer.readUtf());
        }
    };


    public String getText() {
        return text;
    }

    private final String text;

    public DamageParticleData(String text) {
        this.text = text;
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return RegisterParticleTypes.DamageNorber.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeUtf(this.text);
    }

    @Override
    public @NotNull String writeToString() {
        return String.format("%s %s", RegisterParticleTypes.DamageNorber.getId(), this.text);
    }
}
