package mutantescape.level.capability;

import net.minecraft.nbt.CompoundTag;

import java.util.Map;

public interface Capa {
    void copy(Capa cap);
    Map<String, MEAttrubes> getProfession();

    CompoundTag savaNBtData(CompoundTag tag);
    void loadNbtData(CompoundTag tag);
    void add(String id,String name,Double min,Double max,Double value,Boolean s);
}
