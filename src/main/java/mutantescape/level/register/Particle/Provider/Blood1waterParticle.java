package mutantescape.level.register.Particle.Provider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class Blood1waterParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public static Blood1waterParticleProvider provider(SpriteSet spriteSet) {
        return new Blood1waterParticleProvider(spriteSet);
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    protected Blood1waterParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.1F, 0.1F);
        this.quadSize *= 1.5F;
        this.lifetime = Math.max(1, 200 + (this.random.nextInt(200) - 100));
        this.gravity = 0.1F;
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
            this.setSprite(this.spriteSet.get(this.age / 8 % 1 + 1, 1));
        }



    }

    public static class Blood1waterParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Blood1waterParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Blood1waterParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
