package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.effects.MutantEscapeEffect;
import mutantescape.tools.ModSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterEffect {
    public static final DeferredRegister<MobEffect> EFFECTS=DeferredRegister.create(Registries.MOB_EFFECT, MutantEscape.MODID);
    public static final RegistryObject<MobEffect> MUTANT_ESCAPE_EFFECT=EFFECTS.register(ModSet.ModKey("mutant_escape_effect"),()->new MutantEscapeEffect(MobEffectCategory.HARMFUL,7561558));

}
