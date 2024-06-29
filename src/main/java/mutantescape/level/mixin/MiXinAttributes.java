package mutantescape.level.mixin;



import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;


@Mixin(Attributes.class)
public abstract class MiXinAttributes {
    @Inject(method = "register",at = @At("HEAD"), cancellable = true)
    private static void register(String pId, Attribute pAttribute, CallbackInfoReturnable<Attribute> cir) {
        if (Objects.equals(pId, "generic.max_health")){
            cir.setReturnValue(Registry.register(BuiltInRegistries.ATTRIBUTE, pId, new RangedAttribute("attribute.name.generic.max_health", 20.0D, 1.0D, Double.MAX_VALUE).setSyncable(true)));
        }else if (Objects.equals(pId, "generic.armor")){
            cir.setReturnValue(Registry.register(BuiltInRegistries.ATTRIBUTE, pId, new RangedAttribute("attribute.name.generic.armor", 0.0D, 0.0D, Double.MAX_VALUE).setSyncable(true)));
        }
    }
}
