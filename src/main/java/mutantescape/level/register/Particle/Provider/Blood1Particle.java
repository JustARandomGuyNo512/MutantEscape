package mutantescape.level.register.Particle.Provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Blood1Particle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public static Blood1ParticleProvider provider(SpriteSet spriteSet) {
        return new Blood1ParticleProvider(spriteSet);
    }


    public static class Blood1ParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Blood1ParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Blood1Particle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }



    protected Blood1Particle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.1F, 0.1F);
        this.quadSize *= 1.5F;
        this.lifetime = Math.max(1, 150 + (this.random.nextInt(140) - 70));
        this.gravity = 0.4F;
        this.hasPhysics  = true;
        this.xd = vx * 0.3;
        this.yd = vy * 0.3;
        this.zd = vz * 0.3;
        this.setSpriteFromAge(spriteSet);
    }



    @Override
    public void tick() {
        super.tick();
        if (!this.removed){
            this.setSprite((this.spriteSet.get(this.age / 8 % 1 + 1,1)));
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
