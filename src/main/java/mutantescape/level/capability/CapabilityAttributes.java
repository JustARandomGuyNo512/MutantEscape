package mutantescape.level.capability;

import mutantescape.tools.ModSet;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.HashMap;
import java.util.Map;

public class CapabilityAttributes implements Capability {
    private Map<String, Attribute> Data=new HashMap<>();

    private boolean needSync = false;

    public CapabilityAttributes() {
        if (getSize()<=1) {
            add("", "evolution_stage_value", 0.0d, Double.MAX_VALUE, 100.0, false);
            add("", "protection_period", 0.0d, Double.MAX_VALUE, 7.0, false);
            add("", "reason_value", -100.0d, Double.MAX_VALUE, 0.0, false);
        }
    }

    @Override
    public boolean isNeedSync() {
        return needSync;
    }

    @Override
    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    @Override
    public void copy(Capability cap) {
        Map<String, Attribute> other = cap.getProfession();
        Data.putAll(other);
    }

    @Override
    public Map<String, Attribute> getProfession() {
        return Data;
    }

    @Override
    public CompoundTag saveNbtData(CompoundTag tag) {
        if (this.Data != null) {
            ListTag tagList = new ListTag();
            this.Data.values().forEach(tab -> {
                CompoundTag dataTag = new CompoundTag();
                dataTag.putString("id", tab.getID());
                dataTag.putString("name", tab.getName());
                dataTag.putDouble("min", tab.getMin());
                dataTag.putDouble("max", tab.getMax());
                dataTag.putDouble("value", tab.getValue());
                dataTag.putBoolean("start",tab.isStart());
                dataTag.putBoolean("onlyC2S",tab.isOnlyC2S());
                tagList.add(dataTag);
            });
            tag.put(ModSet.ModKey("attribute"), tagList);
        }
        return tag;
    }

    @Override
    public void loadNbtData(CompoundTag tag) {
        if (this.Data==null){
            this.Data=new HashMap<>();
        }
        if (tag.contains(ModSet.ModKey("attribute"), 9)) {
            ListTag dataList = tag.getList(ModSet.ModKey("attribute"), 10);
            dataList.forEach(dataTag -> {
                if (dataTag instanceof CompoundTag compoundData) {
                    String id = compoundData.getString("id");
                    String name = compoundData.getString("name");
                    double min = compoundData.getDouble("min");
                    double max = compoundData.getDouble("max");
                    double value = compoundData.getDouble("value");
                    boolean start=compoundData.getBoolean("start");
                    boolean onlyC2S = compoundData.getBoolean("onlyC2S");
                    this.Data.put(id,create(id,name,min,max,value,start, onlyC2S));
                }
            });
        }
    }
    @Override
    public void add(String name, String id, Double min, Double max, Double value, Boolean s) {
        add(name, id,min,max,value,s, false);
    }

    @Override
    public void add(String name, String id, Double min, Double max, Double value, Boolean s, Boolean onlyC2S) {
        this.Data.put(id,create(id,name,min,max,value,s, onlyC2S));
    }

    public static Attribute create(String id, String name, Double min, Double max, Double value, boolean start, boolean onlyC2S){
        Attribute TAB= new Attribute();
        TAB.ID=id;
        TAB.name =name;
        TAB.max=max;
        TAB.min=min;
        TAB.value=value;
        TAB.start=start;
        TAB.onlyC2S = onlyC2S;
        return TAB;
    }

    public int getSize(){
        return Data.size();
    }

    public Attribute get(String ID) {
        return Data.get(ID);
    }

    public void setAttrName(String ID, String name) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.name.equals(name)) {
                return;
            }
            meAttribute.name = name;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public String getAttrName(String ID) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.getName();
        }
        return null;
    }

    public void setAttrMax(String ID, double max) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.max == max) {
                return;
            }
            meAttribute.max = max;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public double getAttrMax(String ID) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.getMax();
        }
        return -1;
    }

    public void setAttrMin(String ID, double min) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.min == min) {
                return;
            }
            meAttribute.min = min;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public double getAttrMin(String ID) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.getMin();
        }
        return -1;
    }

    public void setAttrValue(String ID, double value) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.value == value) {
                return;
            }
            meAttribute.value = value;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public double getAttrValue(String ID) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.getValue();
        }
        return -1;
    }

    public void setAttrStart(String ID, boolean start) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.start == start) {
                return;
            }
            meAttribute.start = start;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public boolean getAttrStart(String ID) {
        Attribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.start;
        }
        return false;
    }

}
