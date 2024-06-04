package mutantescape.tools.Utils;

import mutantescape.tools.ModSet;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Nullable;
import java.util.*;

public class MCUtils {
    public MCUtils() {
    }


    public static double getMovementSpeedRatio(LivingEntity livingEntity) {
        double baseMS = 0.1;
        if (livingEntity.isSprinting()) {
            baseMS += 0.029999999329447746;
        }

        double entityMS = livingEntity.getAttributeValue(Attributes.MOVEMENT_SPEED);
        return entityMS / baseMS;
    }

    public static void addAttributeModifierToItemStack(ItemStack itemStack, Attribute attribute, AttributeModifier modifier, EquipmentSlot modifierSlot) {
        if (itemStack.hasTag() && !itemStack.getTag().contains("AttributeModifiers", 9)) {
            Iterator var4 = itemStack.getAttributeModifiers(modifierSlot).entries().iterator();

            while(var4.hasNext()) {
                Map.Entry<Attribute, AttributeModifier> entry = (Map.Entry)var4.next();
                itemStack.addAttributeModifier((Attribute)entry.getKey(), (AttributeModifier)entry.getValue(), modifierSlot);
            }
        }

        itemStack.addAttributeModifier(attribute, modifier, modifierSlot);
    }

    public static boolean applyModifier(LivingEntity entity, Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation operation, boolean permanent) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            if (attributeInstance.getModifier(uuid) != null) {
                return false;
            } else {
                AttributeModifier modifier = new AttributeModifier(uuid, name, amount, operation);
                if (permanent) {
                    attributeInstance.addPermanentModifier(modifier);
                } else {
                    attributeInstance.addTransientModifier(modifier);
                }

                if (attribute == Attributes.MAX_HEALTH) {
                    entity.setHealth(entity.getMaxHealth());
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean applyModifier(LivingEntity entity, Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation operation) {
        return applyModifier(entity, attribute, uuid, name, amount, operation, true);
    }

    public static boolean setAttributeValue(LivingEntity entity, Attribute attribute, double value) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(value);
            if (attribute == Attributes.MAX_HEALTH) {
                entity.setHealth(entity.getMaxHealth());
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean hurtIgnoreInvuln(LivingEntity hurtEntity, DamageSource source, float amount) {
        int hurtResistantTime = hurtEntity.invulnerableTime;
        hurtEntity.invulnerableTime = 0;
        boolean attacked = hurtEntity.hurt(source, amount);
        hurtEntity.invulnerableTime = hurtResistantTime;
        return attacked;
    }

    public static boolean compareNBT(CompoundTag nbt1, CompoundTag nbt2) {
        Iterator var2 = nbt1.getAllKeys().iterator();

        String key;
        label28:
        do {
            do {
                if (!var2.hasNext()) {
                    return true;
                }

                key = (String)var2.next();
                if (!nbt2.contains(key)) {
                    return false;
                }

                if (nbt1.get(key) instanceof CompoundTag && nbt2.get(key) instanceof CompoundTag) {
                    continue label28;
                }
            } while(nbt1.get(key).equals(nbt2.get(key)));

            return false;
        } while(compareNBT(nbt1.getCompound(key), nbt2.getCompound(key)));

        return false;
    }

    public static boolean isAdvancementDone(ServerPlayer player, ResourceLocation advancementRL) {
        Advancement advancement = player.server.getAdvancements().getAdvancement(advancementRL);
        return advancement == null ? false : player.getAdvancements().getOrStartProgress(advancement).isDone();
    }

    public static ItemStack setCustomEffects(ItemStack itemStack, Collection<MobEffectInstance> mobEffectInstances) {
        if (!mobEffectInstances.isEmpty()) {
            CompoundTag compoundtag = itemStack.getOrCreateTag();
            ListTag listtag = compoundtag.getList("CustomPotionEffects", 9);
            Iterator var4 = mobEffectInstances.iterator();

            while(var4.hasNext()) {
                MobEffectInstance mobeffectinstance = (MobEffectInstance)var4.next();
                listtag.add(mobeffectinstance.save(new CompoundTag()));
            }

            compoundtag.putInt("CustomPotionColor", PotionUtils.getColor(mobEffectInstances));
            compoundtag.put("CustomPotionEffects", listtag);
        }

        return itemStack;
    }

    public static boolean hasNegativeEffect(LivingEntity entity) {
        Iterator var1 = entity.getActiveEffects().iterator();

        MobEffectInstance mobEffectInstance;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            mobEffectInstance = (MobEffectInstance)var1.next();
        } while(!entity.hasEffect(mobEffectInstance.getEffect()) || !mobEffectInstance.getEffect().getCategory().equals(MobEffectCategory.HARMFUL));

        return true;
    }

    public static boolean hasLongNegativeEffect(LivingEntity entity) {
        Iterator var1 = entity.getActiveEffects().iterator();

        MobEffectInstance mobEffectInstance;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            mobEffectInstance = (MobEffectInstance)var1.next();
        } while(!entity.hasEffect(mobEffectInstance.getEffect()) || !mobEffectInstance.getEffect().getCategory().equals(MobEffectCategory.HARMFUL) || mobEffectInstance.getDuration() <= 150);

        return true;
    }

    public static int getFittingY(EntityType<?> entityType, BlockPos pos, Level level, int minRelativeY) {
        int height = (int)Math.ceil((double)entityType.getHeight());
        int fittingYPos = level.getMinBuildHeight() - 1;

        for(int y = pos.getY(); y > pos.getY() - minRelativeY; --y) {
            boolean viable = true;
            BlockPos p = new BlockPos(pos.getX(), y, pos.getZ());

            for(int i = 0; i < height; ++i) {
                if (level.getBlockState(p.above(i)).blocksMotion()) {
                    viable = false;
                    break;
                }
            }

            if (viable) {
                fittingYPos = y;
                if (level.getBlockState(p.below()).blocksMotion()) {
                    return y;
                }
            }
        }

        return fittingYPos;
    }

    public static int getEnchantmentLevel(ResourceLocation enchantmentId, ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            ListTag listTag = stack.getEnchantmentTags();

            for(int i = 0; i < listTag.size(); ++i) {
                CompoundTag compoundTag = listTag.getCompound(i);
                ResourceLocation itemEnchantment = ResourceLocation.tryParse(compoundTag.getString("id"));
                if (itemEnchantment != null && itemEnchantment.equals(enchantmentId)) {
                    return Mth.clamp(compoundTag.getInt("lvl"), 0, 255);
                }
            }

            return 0;
        }
    }








    public static MobEffectInstance createEffectInstance(MobEffect potion, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, boolean canBeCured) {
        MobEffectInstance effectInstance = new MobEffectInstance(potion, duration, amplifier, ambient, showParticles, showIcon);
        if (!canBeCured) {
            effectInstance.setCurativeItems(new ArrayList());
        }

        return effectInstance;
    }

    public static ArrayList<MobEffectInstance> parseMobEffectsList(List<? extends String> list) {
        ArrayList<MobEffectInstance> mobEffectInstances = new ArrayList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            MobEffectInstance mobEffectInstance = parseEffectInstance(s);
            if (mobEffectInstance != null) {
                mobEffectInstances.add(mobEffectInstance);
            }
        }

        return mobEffectInstances;
    }

    @Nullable
    public static MobEffectInstance parseEffectInstance(String s) {
        String[] split = s.split(",");
        if (split.length != 3) {
            ModSet.Warn("Invalid Mob Effect \"%s\"", new Object[]{s});
            return null;
        } else {
            ResourceLocation effectRL = ResourceLocation.tryParse(split[0]);
            if (effectRL == null) {
                ModSet.Warn("%s mob effect is not valid", new Object[]{split[0]});
                return null;
            } else if (!ForgeRegistries.MOB_EFFECTS.containsKey(effectRL)) {
                ModSet.Warn("%s mob effect seems to not exist", new Object[]{split[0]});
                return null;
            } else {
                MobEffect effect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(effectRL);
                if (!NumberUtils.isParsable(split[1])) {
                    ModSet.Warn(String.format("Invalid duration \"%s\" for Mob Effect", s), new Object[0]);
                    return null;
                } else {
                    int duration = Integer.parseInt(split[1]);
                    if (!NumberUtils.isParsable(split[2])) {
                        ModSet.Warn(String.format("Invalid amplifier \"%s\" for Mob Effect", s), new Object[0]);
                        return null;
                    } else {
                        int amplifier = Integer.parseInt(split[2]);
                        return new MobEffectInstance(effect, duration, amplifier);
                    }
                }
            }
        }
    }
}

