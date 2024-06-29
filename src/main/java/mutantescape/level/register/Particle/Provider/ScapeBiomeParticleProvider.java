package mutantescape.level.register.Particle.Provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class ScapeBiomeParticleProvider extends TextureSheetParticle {
    public static SscrapeParticleProvider provider(SpriteSet spriteSet) {
        return new SscrapeParticleProvider(spriteSet);
    }
    public static class SscrapeParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public SscrapeParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ScapeBiomeParticleProvider(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

    private final SpriteSet spriteSet;
    private float angularVelocity;
    private float angularAcceleration;

    public ScapeBiomeParticleProvider(ClientLevel pLevel, double pX, double pY, double pZ, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ);
        this.spriteSet = spriteSet;
        this.setSize(0.2f, 0.2f);
        this.lifetime = 20;
        this.gravity = 0.134f;
        this.hasPhysics = true;
        this.xd = vx * 0.5;
        this.yd = vy * 0.5;
        this.zd = vz * 0.5;
        this.angularVelocity = 0.134f;
        this.angularAcceleration = 0f;
        this.pickSprite(spriteSet);

    }

    @Override
    public int getLightColor(float partialTick) {
        return 15728880;
    }


    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }


    @Override
    public void tick() {
        super.tick();
        this.oRoll = this.roll;
        this.roll += this.angularVelocity;
        this.angularVelocity += this.angularAcceleration;
    }


}
