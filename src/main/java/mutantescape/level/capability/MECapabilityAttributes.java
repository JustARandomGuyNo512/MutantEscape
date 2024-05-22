package mutantescape.level.capability;

import mutantescape.client.config.MEConfig;
import mutantescape.tools.IkeySet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.HashMap;
import java.util.Map;

public class MECapabilityAttributes implements ICapability {
    private Map<String, MEAttribute> Data=new HashMap<>();
    private boolean needSync = false;

    public MECapabilityAttributes() {
        add("","evolution_stage_value",0.0d, MEConfig.stage_value,MEConfig.stage_value,false);
        add("","protection_period",0.0d, Double.valueOf(MEConfig.protection_period),Double.valueOf(MEConfig.protection_period),false);
        add("","reason_value",-100.0d, MEConfig.reason_value,MEConfig.reason_value,false);

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
    public void copy(ICapability cap) {
        Data = cap.getProfession();
    }

    @Override
    public Map<String, MEAttribute> getProfession() {
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
            tag.put(IkeySet.ModKey("attribute"), tagList);
        }
        return tag;
    }

    @Override
    public void loadNbtData(CompoundTag tag) {
        if (this.Data==null){
            this.Data=new HashMap<>();
        }
        if (tag.contains(IkeySet.ModKey("attribute"), 9)) {
            ListTag dataList = tag.getList(IkeySet.ModKey("attribute"), 10);
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

    public static MEAttribute create(String id, String name, Double min, Double max, Double value, boolean start, boolean onlyC2S){
        MEAttribute TAB= new MEAttribute();
        TAB.ID=id;
        TAB.name =name;
        TAB.max=max;
        TAB.min=min;
        TAB.value=value;
        TAB.start=start;
        TAB.onlyC2S = onlyC2S;
        return TAB;
    }

    public MEAttribute get(String ID) {
        return Data.get(ID);
    }

    public void setAttrName(String ID, String name) {
        MEAttribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.name.equals(name)) {
                return;
            }
            meAttribute.name = name;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public void setAttrMax(String ID, double max) {
        MEAttribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.max == max) {
                return;
            }
            meAttribute.max = max;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

    public void setAttrMin(String ID, double min) {
        MEAttribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.min == min) {
                return;
            }
            meAttribute.min = min;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }
    public void setAttrValue(String ID, double value) {
        MEAttribute meAttribute = get(ID);
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
        MEAttribute meAttribute = get(ID);
        if (meAttribute != null) {
            return meAttribute.getValue();
        }
        return -1;
    }

    public void setAttrStart(String ID, boolean start) {
        MEAttribute meAttribute = get(ID);
        if (meAttribute != null) {
            if (meAttribute.start == start) {
                return;
            }
            meAttribute.start = start;
            meAttribute.needSync = true;
            this.needSync = true;
        }
    }

}
