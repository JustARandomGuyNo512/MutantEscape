package mutantescape.level.register.effects;

import mutantescape.tools.ModSet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MutantEscapeEffect extends MobEffect {
    private int tick;
    public MutantEscapeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
       //ModSet.Info("效果持续:"+pDuration+"|"+pAmplifier);
       if (pDuration<=2){

       }
        return super.isDurationEffectTick(pDuration, pAmplifier);
    }


}
