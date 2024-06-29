package mutantescape.level.function;

import mutantescape.level.register.RegisterBiome;
import mutantescape.level.register.RegisterBlock;
import mutantescape.level.register.RegisterParticleTypes;
import mutantescape.tools.ModSet;
import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;


import java.util.*;


public class BiomeFunction extends BiomeUtils {
     private static HashSet<BlockPos> Aovpos=new HashSet<>();
     public static HashSet<Block> BanBlock=new HashSet<>();
     public static HashMap<Block,Block> ToScapeList=new HashMap<>();
     public static ServerLevel level;
     public static final int ScapeMaxSize=100;
     public static int ScapeValue;
     public static int Time;
    public static int MaxTime=20;

    public BiomeFunction() {
            ForgeRegistries.BLOCKS.getValues().forEach(
                    block -> {
                        addScapeBlock(block, RegisterBlock.INFECTED_LEAVES.get(), BlockTags.LEAVES);
                        addScapeBlock(block, RegisterBlock.INFECTED_TREE1_BLOCK.get(), BlockTags.LOGS);
                        addScapeBlock(block, RegisterBlock.INFECTED_STONE_BLOCK.get(), BlockTags.BASE_STONE_OVERWORLD);
                        addScapeBlock(block, RegisterBlock.INFECTED_FLOWER.get(), BlockTags.FLOWERS);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.IRON_ORES);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.COAL_ORES);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.COPPER_ORES);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.DIAMOND_ORES);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.GOLD_ORES);
                        addScapeBlock(block,RegisterBlock.INFECTED_IRON_BLOCK.get(),BlockTags.LAPIS_ORES);
                    }
            );
            addScapeBlock(Blocks.GRASS_BLOCK, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            addScapeBlock(Blocks.GRASS, RegisterBlock.INFECTED_GRASS.get());
            addScapeBlock(Blocks.SAND, RegisterBlock.INFECTED_FALLING_BLOCK.get());
            addScapeBlock(Blocks.DIRT,RegisterBlock.INFECTED_GRASS_BLOCK.get());
    }

    public static void addPos(ServerLevel Serverlevel,BlockPos pos){
        level = Serverlevel;
        if (level!=null) {
            BlockPos randomPos = pos.relative(Direction.getRandom(RandomSource.createNewThreadLocalInstance()));
           if (Aovpos.size()<=ScapeMaxSize && !PosISModBlock(randomPos) && Time<=MaxTime){
               Aovpos.add(randomPos);
           }
        }
    }


    @SafeVarargs
    private static void addScapeBlock(Block block,Block STartBlock, @Nullable TagKey<Block>... tag) {
        if (tag==null || tag.length<1){
        ToScapeList.put(block, STartBlock);
        }else {
            Arrays.stream(tag).forEach(
                    tagKey -> {
                        if (tagKey!=null) {
                            if (block.defaultBlockState().getBlockHolder().is(tagKey)){
                              if (!ToScapeList.containsKey(block)) {
                                  ToScapeList.put(block, STartBlock);
                              }
                            }
                        }
                    }
            );
        }
    }



    public static void ScapeBlockPos(){
            Aovpos.forEach(
                    pos -> {
                        List<ServerPlayer> Players = level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false);
                        Block TartBlock = ToScapeList.get(level.getBlockState(pos).getBlock());
                        if (!BanBlock.contains(TartBlock) && TartBlock!=null) {
                            level.setBlock(pos, TartBlock.defaultBlockState(), 16);
                            setBiome(level,pos, RegisterBiome.SPACE_BIOMES.getKey());
                            Players.forEach(
                                    player -> {
                                        player.connection.send(new ClientboundBlockUpdatePacket(level,pos));
                                        ScapeValue++;
                                    }
                            );
                        }
                    }
            );
            Aovpos.clear();
    }

   public static boolean PosISModBlock(BlockPos pos){
       return ToScapeList.containsValue(level.getBlockState(pos).getBlock())
               || ToScapeList.get(level.getBlockState(pos).getBlock())==null
               || Aovpos.contains(pos)
               ;
   }




}
