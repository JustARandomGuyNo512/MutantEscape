package mutantescape.tools;

import com.mojang.logging.LogUtils;
import io.netty.buffer.Unpooled;
import mutantescape.MutantEscape;
import mutantescape.network.PacketHandler;
import mutantescape.network.s2c.UpdataBiomePaket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.*;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.Random;

public class ModSet {
    public static ServerPlayer ModServerPlayer;
    public static  Minecraft MC;
    static {
        MC= Minecraft.getInstance();
            if (MC.level!=null && MC.level.getServer() != null && MC.player != null) {
                ModServerPlayer = MC.level.getServer().getPlayerList().getPlayer(MC.player.getUUID());

            }
    }
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void LocatePlayerSendLog(Component debug){
                if (ModServerPlayer!=null){
                    ModServerPlayer.sendSystemMessage(debug);
                }
    }






    public static String ModKey(String key){
        return MutantEscape.MODID+"_" +key.toLowerCase().replace(" ","_");
    }

    public static void Debug(String log,int Type){
        if (Type==0){
            LOGGER.debug(log);
        } else if (Type==1) {
            LOGGER.error(log);
        }else if (Type==2) {
            LOGGER.warn(log);
        }
    }




}
