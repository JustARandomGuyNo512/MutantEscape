package mutantescape.level.network.c2s;

import mutantescape.level.capability.Attribute;
import mutantescape.level.capability.CapabilityProvider;
import mutantescape.level.network.IPacket;
import mutantescape.tools.ModSet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SyncAttributesToServerPacket implements IPacket<SyncAttributesToServerPacket> {
    public List<Attribute> attributes;

    public  SyncAttributesToServerPacket() {}

    public SyncAttributesToServerPacket(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void encode(SyncAttributesToServerPacket message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.attributes.size());
        for (Attribute attribute : message.attributes) {
            attribute.serialize(buffer);
        }
    }

    @Override
    public SyncAttributesToServerPacket decode(FriendlyByteBuf buffer) {
        int size = buffer.readInt();
        List<Attribute> attributes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            attributes.add(Attribute.deserialize(buffer));
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
                player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    boolean markNeedBroadCast = false;
                    for (Attribute attribute : message.attributes) {
                        Attribute target = capability.getProfession().get(attribute.getID());
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
