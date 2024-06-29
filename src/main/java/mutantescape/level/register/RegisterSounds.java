package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.ModSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MutantEscape.MODID);

    public static final RegistryObject<SoundEvent> DEATH_SOUND = registerSoundEvent(ModSet.ModKey("death_sound"));

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MutantEscape.MODID, name)));
    }
}
