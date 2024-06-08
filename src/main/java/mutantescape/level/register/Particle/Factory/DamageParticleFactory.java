package mutantescape.level.register.Particle.Factory;

import mutantescape.level.register.Particle.ParticleType.DamageParticleData;
import mutantescape.level.register.Particle.TextParticle;
import mutantescape.tools.ModSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DamageParticleFactory implements ParticleProvider<DamageParticleData> {
    @Nullable private final SpriteSet spriteSet;

    public DamageParticleFactory(@Nullable SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }
    @Override
    public Particle createParticle(DamageParticleData pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        return new TextParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.spriteSet, pType.getText());
    }
}
