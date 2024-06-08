package mutantescape.tools;

import com.mojang.logging.LogUtils;
import io.netty.buffer.Unpooled;
import mutantescape.MutantEscape;
import mutantescape.network.PacketHandler;
import mutantescape.network.s2c.UpdataBiomePaket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;

import java.util.*;

public class ModSet {
    public static ServerPlayer ModServerPlayer;
    private static final Set<Block> TRANSPARENT_BLOCKS = new HashSet<>();
    public static  Minecraft MC;
    static {
        MC=Minecraft.getInstance();
        if (MC!=null) {
            if (MC.level != null && MC.level.getServer() != null && MC.player != null) {
                ModServerPlayer = MC.level.getServer().getPlayerList().getPlayer(MC.player.getUUID());

            }
            if (!TRANSPARENT_BLOCKS.contains(Blocks.GLASS)) {
                TRANSPARENT_BLOCKS.add(Blocks.GLASS);
                TRANSPARENT_BLOCKS.add(Blocks.GLASS_PANE);
                TRANSPARENT_BLOCKS.add(Blocks.OAK_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.BIRCH_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.SPRUCE_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.JUNGLE_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.ACACIA_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.DARK_OAK_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.MANGROVE_LEAVES);
                TRANSPARENT_BLOCKS.add(Blocks.GRASS);
                TRANSPARENT_BLOCKS.add(Blocks.VINE);
            }
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
    public static void  Debug(String log){
        LOGGER.debug(log);
    }
    public static void  Error(String log){
        LOGGER.error(log);
    }

    public static void  Warn(String log){
        LOGGER.warn(log);
    }


    public static void Error(String format, Object... args) {
        LOGGER.error(String.format(format, args));
    }

    public static void Warn(String format, Object... args) {
        LOGGER.warn(String.format(format, args));
    }

    public static void Info(String format, Object... args) {
        LOGGER.info(String.format(format, args));
    }

    public static boolean isSurface(Level world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();

        if (!blockState.isAir() && !isTransparent(block)) {
            return false;
        }

        BlockPos belowPos = pos.below();
        BlockState belowBlockState = world.getBlockState(belowPos);
        Block belowBlock = belowBlockState.getBlock();

        if (belowBlockState.isAir() || isTransparent(belowBlock)) {
            return false;
        }
        BlockPos topPos = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos);
        if (!topPos.equals(pos)) {
            return false;
        }

        return true;
    }
    private static boolean isTransparent(Block block) {
        return TRANSPARENT_BLOCKS.contains(block);
    }


}
