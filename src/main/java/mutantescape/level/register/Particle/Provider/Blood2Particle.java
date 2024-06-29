package mutantescape.level.register.Particle.Provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Blood2Particle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public static Blood2ParticleProvider provider(SpriteSet spriteSet) {
        return new Blood2ParticleProvider(spriteSet);
    }


    protected Blood2Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.1F, 0.1F);
        this.quadSize *= 0.8F;
        this.lifetime = Math.max(1, 200 + (this.random.nextInt(300) - 150));
        this.gravity = 0.4F;
        this.hasPhysics = true;
        this.xd = vx * 0.3;
        this.yd = vy * 0.3;
        this.zd = vz * 0.3;
        this.setSpriteFromAge(spriteSet);
    }


    @Override
    public void tick() {
        super.tick();
        if (!this.removed){
            this.setSprite(this.spriteSet.get(this.age / 13 % 1 + 1, 1));
        }







    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    public static class Blood2ParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Blood2ParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Blood2Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

}
