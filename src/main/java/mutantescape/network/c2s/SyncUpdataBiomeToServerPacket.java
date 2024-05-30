package mutantescape.network.c2s;

import mutantescape.network.IPacket;
import mutantescape.network.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncUpdataBiomeToServerPacket implements IPacket<SyncUpdataBiomeToServerPacket> {
    int chunkX;
    int chunkY;

    public SyncUpdataBiomeToServerPacket() {

    }

    public SyncUpdataBiomeToServerPacket(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }



    @Override
    public void encode(SyncUpdataBiomeToServerPacket message, FriendlyByteBuf buffer) {
        buffer.writeInt(this.chunkX);
        buffer.writeInt(this.chunkY);
    }

    @Override
    public SyncUpdataBiomeToServerPacket decode(FriendlyByteBuf buffer) {
        return new SyncUpdataBiomeToServerPacket(buffer.readInt(),buffer.readInt());
    }

    @Override
    public void handle(SyncUpdataBiomeToServerPacket message, Supplier<NetworkEvent.Context> supplier) {

    }
}
