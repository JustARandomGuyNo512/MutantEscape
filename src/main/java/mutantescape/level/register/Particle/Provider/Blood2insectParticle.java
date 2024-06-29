package mutantescape.level.register.Particle.Provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Blood2insectParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public static Blood2insectParticleProvider provider(SpriteSet spriteSet) {
        return new Blood2insectParticleProvider(spriteSet);
    }





    protected Blood2insectParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.1F, 0.1F);
        this.quadSize *= 0.8F;
        this.lifetime = Math.max(1, 200 + (this.random.nextInt(300) - 150));
        this.gravity = 0.4F;
        this.hasPhysics = true;
        this.yd = vx * 0.3;
        this.xd = vy * 0.3;
        this.zd = vz * 0.3;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @Override
    public void tick() {
        super.tick();
        if (!this.removed){
            this.setSprite(this.spriteSet.get(this.age / 13 % 1 + 1, 1));
        }
    }

    public static class Blood2insectParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Blood2insectParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Blood2insectParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}

