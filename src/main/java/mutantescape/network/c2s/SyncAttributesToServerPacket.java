package mutantescape.network.c2s;

import mutantescape.level.capability.MEAttribute;
import mutantescape.level.capability.MECapabilityProvider;
import mutantescape.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SyncAttributesToServerPacket implements IPacket<SyncAttributesToServerPacket> {
    public List<MEAttribute> attributes;

    public  SyncAttributesToServerPacket() {}

    public SyncAttributesToServerPacket(List<MEAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void encode(SyncAttributesToServerPacket message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.attributes.size());
        for (MEAttribute attribute : message.attributes) {
            attribute.serialize(buffer);
        }
    }

    @Override
    public SyncAttributesToServerPacket decode(FriendlyByteBuf buffer) {
        int size = buffer.readInt();
        List<MEAttribute> attributes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            attributes.add(MEAttribute.deserialize(buffer));
        }
        return new SyncAttributesToServerPacket(attributes);
    }

    @Override
    public void handle(SyncAttributesToServerPacket message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(() -> {
            if (message.attributes.isEmpty()) {
                return;
            }
            ServerPlayer player = supplier.get().getSender();
            if (player != null) {
                player.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    boolean markNeedBroadCast = false;
                    for (MEAttribute attribute : message.attributes) {
                        MEAttribute target = capability.getProfession().get(attribute.getID());
                        if (target != null) {
                            target.copyFrom(attribute);
                            if (!target.isOnlyC2S()) {
                                target.setNeedSync(true);
                                markNeedBroadCast = true;
                            } else {
                                target.setNeedSync(false);
                            }
                        }
                    }
                    capability.setNeedSync(markNeedBroadCast);
                }));
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
