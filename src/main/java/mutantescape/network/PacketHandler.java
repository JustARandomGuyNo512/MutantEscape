package mutantescape.network;

import mutantescape.MutantEscape;
import mutantescape.network.c2s.SyncAttributesToServerPacket;
import mutantescape.network.s2c.BroadcastAttributesToClientPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler
{
    public static final String PROTOCOL_VERSION = MutantEscape.MODID + "1.0";
    public static SimpleChannel simpleChannel;
    private static int tempId;

    public static void register()
    {
        simpleChannel = NetworkRegistry.newSimpleChannel(new ResourceLocation(MutantEscape.MODID, "common"), ()-> PROTOCOL_VERSION,
                (s) ->true, (s) ->true);
        registerPacket(SyncAttributesToServerPacket.class, new SyncAttributesToServerPacket());
        registerPacket(BroadcastAttributesToClientPacket.class, new BroadcastAttributesToClientPacket());
    }

    private static <T> void registerPacket(Class<T> clazz, IPacket<T> message) {
        simpleChannel.registerMessage(tempId++, clazz, message::encode, message::decode, message::handle);
    }

    public static <MSG> void sendToServer(MSG message){
        simpleChannel.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        simpleChannel.send(PacketDistributor.PLAYER.with(()->player),message);
    }
}