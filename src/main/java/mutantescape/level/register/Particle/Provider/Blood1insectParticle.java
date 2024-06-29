package mutantescape.level.register.Particle.Provider;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class Blood1insectParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    public static Blood1insectParticleProvider provider(SpriteSet spriteSet) {
        return new Blood1insectParticleProvider(spriteSet);
    }
    protected Blood1insectParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.1F, 0.1F);//m_107250_
        this.lifetime = Math.max(1, 150 + (this.random.nextInt(140) - 70));//f_107225_//this.random 188503
        this.gravity = 0.4f;//f_107226_
        this.hasPhysics = true;//f_107219_
        this.quadSize*= 1.5F;//f_107663_
        this.xd = vx * 0.3;//f_107215_
        this.yd = vy * 0.3;//f_107216_
        this.zd = vz * 0.3;//f_107217_
        this.setSpriteFromAge(spriteSet);//m_108339_
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {

        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;//107430
    }

    @Override
    public void tick() {
        super.tick();//5989
        //this.age 107224
        //this.spriteSet.get 5819
        //this.setSprite(); m_108337_
        //this.removed 107220
        if (!this.removed) {
            this.setSprite((this.spriteSet.get(this.age / 8 % 1 + 1,1)));
        }
    }

    public static class Blood1insectParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Blood1insectParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new Blood1insectParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }


}
