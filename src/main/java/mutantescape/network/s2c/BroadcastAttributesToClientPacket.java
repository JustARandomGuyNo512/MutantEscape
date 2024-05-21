package mutantescape.network.s2c;

import mutantescape.Clients;
import mutantescape.level.capability.MEAttribute;
import mutantescape.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BroadcastAttributesToClientPacket implements IPacket<BroadcastAttributesToClientPacket> {
    List<MEAttribute> attributeList;
    int entityID;

    public BroadcastAttributesToClientPacket() {}

    public BroadcastAttributesToClientPacket(List<MEAttribute> attributeList, int entityID) {
        this.attributeList = attributeList;
        this.entityID = entityID;
    }

    @Override
    public void encode(BroadcastAttributesToClientPacket message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.attributeList.size());
        buffer.writeInt(message.entityID);
        for (MEAttribute attribute : message.attributeList) {
            attribute.serialize(buffer);
        }
    }

    @Override
    public BroadcastAttributesToClientPacket decode(FriendlyByteBuf buffer) {
        int size = buffer.readInt();
        int entityID = buffer.readInt();
        List<MEAttribute> attributes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            attributes.add(MEAttribute.deserialize(buffer));
        }
        return new BroadcastAttributesToClientPacket(attributes, entityID);
    }

    @Override
    public void handle(BroadcastAttributesToClientPacket message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    Clients.updateClientPlayerAttributes(message.entityID, message.attributeList));
        });
        supplier.get().setPacketHandled(true);
    }
}
