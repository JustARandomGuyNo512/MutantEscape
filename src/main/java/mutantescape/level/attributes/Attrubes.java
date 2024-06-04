package mutantescape.level.attributes;

import mutantescape.tools.ModSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class Attrubes {


    public static final Attribute XRAY_FOLLOW_RANGE = (new RangedAttribute("attribute.name.generic.xray_follow_range", 0d, 0d, 512d)).setSyncable(true);
    public static final Attribute EVOLUTION_VALUE = (new RangedAttribute("attribute.name.generic.evolution_value", 0d, 0d, 10240d)).setSyncable(true);


    public static void Regiister(){
        ForgeRegistries.ATTRIBUTES.register(ModSet.ModKey("xray"),XRAY_FOLLOW_RANGE);
        ForgeRegistries.ATTRIBUTES.register(ModSet.ModKey("evolution"),EVOLUTION_VALUE);
    }

    public static void RangeAttribute(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> entityType : event.getTypes()) {
            if (event.has(entityType, XRAY_FOLLOW_RANGE))
                continue;
            event.add(entityType, XRAY_FOLLOW_RANGE, 0d);
        }
    }
}
