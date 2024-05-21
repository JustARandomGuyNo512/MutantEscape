package mutantescape.level.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.Map;

public interface ICapability {
    boolean isNeedSync();
    void setNeedSync(boolean needSync);
    void copy(ICapability cap);
    Map<String, MEAttribute> getProfession();
    CompoundTag saveNbtData(CompoundTag tag);
    void loadNbtData(CompoundTag tag);
    void add(String id,String name,Double min,Double max,Double value,Boolean s);
    void add(String id,String name,Double min,Double max,Double value,Boolean s, Boolean onlyC2S);
}
