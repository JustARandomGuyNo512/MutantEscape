package mutantescape.level.attributes;

import mutantescape.MutantEscape;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EAttrubes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MutantEscape.MODID);

    public static final RegistryObject<Attribute> XRAY_FOLLOW_RANGE = ATTRIBUTES.register("generic.xray_follow_range", () -> new RangedAttribute("attribute.name.generic.xray_follow_range", 0d, 0d, 256d));

}
