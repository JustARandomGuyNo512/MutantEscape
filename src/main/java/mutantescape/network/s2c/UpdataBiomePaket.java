package mutantescape.network.s2c;

import mutantescape.network.IPacket;
import mutantescape.network.c2s.SyncUpdataBiomeToServerPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdataBiomePaket implements IPacket<SyncUpdataBiomeToServerPacket> {


    @Override
    public void encode(SyncUpdataBiomeToServerPacket message, FriendlyByteBuf buffer) {

    }

    @Override
    public SyncUpdataBiomeToServerPacket decode(FriendlyByteBuf buffer) {
        return null;
    }

    @Override
    public void handle(SyncUpdataBiomeToServerPacket message, Supplier<NetworkEvent.Context> supplier) {
        /* 需要去实现这个操作
       int chunkX = buf.readInt();
            int chunkZ = buf.readInt();
            if (context.getPlayer() != null) {
                ((ClientLevel) context.getPlayer().level()).onChunkLoaded(new ChunkPos(chunkX, chunkZ));
            }
         */
    }

}
