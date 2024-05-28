package mutantescape.tools;

import net.minecraft.nbt.CompoundTag;

public class NBTUtils {
    /**
     * Returns the int read from the compoundTag or if absent puts the defaultValue in the compoundTag and returns it
     */
    public static int getIntOrPutDefault(CompoundTag compoundTag, String tagName, int defaultValue) {
        int result = defaultValue;
        if (compoundTag.contains(tagName)) {
            result = compoundTag.getInt(tagName);
        }
        else {
            compoundTag.putInt(tagName, defaultValue);
        }
        return result;
    }

    /**
     * Returns the double read from the compoundTag or if absent puts the defaultValue in the compoundTag and returns it
     */
    public static double getDoubleOrPutDefault(CompoundTag compoundTag, String tagName, double defaultValue) {
        double result = defaultValue;
        if (compoundTag.contains(tagName)) {
            result = compoundTag.getDouble(tagName);
        }
        else {
            compoundTag.putDouble(tagName, defaultValue);
        }
        return result;
    }

    /**
     * Returns the double read from the compoundTag or if absent puts the defaultValue in the compoundTag and returns it
     */
    public static boolean getBooleanOrPutDefault(CompoundTag compoundTag, String tagName, boolean defaultValue) {
        boolean result = defaultValue;
        if (compoundTag.contains(tagName)) {
            result = compoundTag.getBoolean(tagName);
        }
        else {
            compoundTag.putBoolean(tagName, defaultValue);
        }
        return result;
    }
}
