package mutantescape.network.s2c;

import mutantescape.network.IPacket;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class UpdataBiomePaket implements IPacket<UpdataBiomePaket> {
    private int chunkX;
    private int chunkZ;



    public UpdataBiomePaket() {


    }

    public UpdataBiomePaket(int x, int z) {
        this.chunkX = x;
        this.chunkZ = z;
    }

    @Override
    public void encode(UpdataBiomePaket message, FriendlyByteBuf buffer) {
        buffer.writeInt(this.chunkX);
        buffer.writeInt(this.chunkZ);
    }

    @Override
    public UpdataBiomePaket decode(FriendlyByteBuf buffer) {
        return new UpdataBiomePaket(buffer.readInt(),buffer.readInt());
    }

    @Override
    public void handle(UpdataBiomePaket message, Supplier<NetworkEvent.Context> supplier) {
        @Nullable ServerPlayer player = supplier.get().getSender();
            if (player != null) {
                ((ClientLevel) player.level()).onChunkLoaded(new ChunkPos(chunkX, chunkZ));
            }

    }

}
