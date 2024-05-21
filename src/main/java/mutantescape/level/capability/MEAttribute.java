package mutantescape.level.capability;

import net.minecraft.network.FriendlyByteBuf;

public class MEAttribute {
    String ID;
    String name;
    boolean needSync;
    boolean onlyC2S;

    public boolean isNeedSync() {
        return needSync;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    public boolean isOnlyC2S() {
        return onlyC2S;
    }

    public void setOnlyC2S(boolean onlyC2S) {
        this.onlyC2S = onlyC2S;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    boolean start;
    double min,max,value;
    public static MEAttribute deserialize(FriendlyByteBuf buffer) {//序列化
        MEAttribute table = new MEAttribute();
        table.setID(buffer.readUtf());
        table.setName(buffer.readUtf());
        table.setMin(buffer.readDouble());
        table.setMax(buffer.readDouble());
        table.setValue(buffer.readDouble());
        table.setStart(buffer.readBoolean());
        return table;
    }
    public void serialize(FriendlyByteBuf buf) {//反序列化
        buf.writeUtf(this.ID);
        buf.writeUtf(this.name);
        buf.writeDouble(this.min);
        buf.writeDouble(this.max);
        buf.writeDouble(this.value);
        buf.writeBoolean(this.start);
    }

    public void copyFrom(MEAttribute attribute) {
        if (this.ID.equals(attribute.ID)) {
            this.min = attribute.min;
            this.max = attribute.max;
            this.value = attribute.value;
            this.start = attribute.start;
            this.name = attribute.name;
        }
    }

}
