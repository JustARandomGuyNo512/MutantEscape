package mutantescape.tools.Utils;

import mutantescape.level.network.PacketHandler;
import mutantescape.level.network.s2c.UpdataBiomePaket;
import mutantescape.tools.ModSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.*;

public class BiomeUtils {//下面的方法推荐装入异步执行特别是查找替换删除等
    public static boolean isNaturalTree(ServerLevel level, BlockPos startPos) {
        //检测树是否天然形成
        Queue<BlockPos> queue = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        queue.add(startPos);

        int logCount = 0;
        int leavesCount = 0;

        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();
            if (!visited.contains(currentPos)) {
                visited.add(currentPos);
                BlockState currentState = level.getBlockState(currentPos);
                Block currentBlock = currentState.getBlock();

                if (currentBlock == Blocks.OAK_LOG || currentBlock == Blocks.BIRCH_LOG ||
                        currentBlock == Blocks.SPRUCE_LOG || currentBlock == Blocks.JUNGLE_LOG ||
                        currentBlock == Blocks.ACACIA_LOG || currentBlock == Blocks.DARK_OAK_LOG) {
                    logCount++;
                    for (BlockPos neighborPos : getNeighborPositions(currentPos)) {
                        if (level.isLoaded(neighborPos) && !visited.contains(neighborPos)) {
                            queue.add(neighborPos);
                        }
                    }
                } else if (currentBlock instanceof LeavesBlock) {
                    leavesCount++;
                }
            }
        }
        return logCount > 0 && leavesCount > 0 && leavesCount / logCount >= 1;
    }
    public static void setBiome(ServerLevel serverLevel, BlockPos blockPos, ResourceKey<Biome> biome) {
        //主方法设置群系并更新
        setPosBiome(serverLevel,blockPos,biome);
        updateChunkAfterBiomeChange(serverLevel, new ChunkPos(blockPos));
    }
    public static void setPosBiome(ServerLevel level, BlockPos posIn, ResourceKey<Biome> biome) {
        //辅助设置某个位置的Biome
        int i = posIn.getX() - 2;
        int j = posIn.getY() - 2;
        int k = posIn.getZ() - 2;
        int l = i >> 2;
        int i1 = j >> 2;
        int j1 = k >> 2;
        double d0 = (double)(i & 3) / 4.0D;
        double d1 = (double)(j & 3) / 4.0D;
        double d2 = (double)(k & 3) / 4.0D;
        int k1 = 0;
        double d3 = Double.POSITIVE_INFINITY;

        for(int l1 = 0; l1 < 8; ++l1) {
            boolean flag = (l1 & 4) == 0;
            boolean flag1 = (l1 & 2) == 0;
            boolean flag2 = (l1 & 1) == 0;
            int i2 = flag ? l : l + 1;
            int j2 = flag1 ? i1 : i1 + 1;
            int k2 = flag2 ? j1 : j1 + 1;
            double d4 = flag ? d0 : d0 - 1.0D;
            double d5 = flag1 ? d1 : d1 - 1.0D;
            double d6 = flag2 ? d2 : d2 - 1.0D;
            double d7 = getFiddledDistance(BiomeManager.obfuscateSeed(level.getServer().getWorldData().worldGenOptions().seed()), i2, j2, k2, d4, d5, d6);
            if (d3 > d7) {
                k1 = l1;
                d3 = d7;
            }
        }

        int l2 = (k1 & 4) == 0 ? l : l + 1;
        int i3 = (k1 & 2) == 0 ? i1 : i1 + 1;
        int j3 = (k1 & 1) == 0 ? j1 : j1 + 1;

        // Update biome data in chunk
        ChunkAccess chunk = level.getChunk(QuartPos.toSection(l2), QuartPos.toSection(j3), ChunkStatus.BIOMES, false);
        if (chunk instanceof ImposterProtoChunk) {
            chunk = ((ImposterProtoChunk) chunk).getWrapped();
        }
        if(chunk != null) {
            Registry<Biome> biomeRegistry = level.registryAccess().registryOrThrow(Registries.BIOME);
            Optional<Holder.Reference<Biome>> biomeHack = biomeRegistry.getHolder(biome);
            if (biomeHack.isEmpty()) {
                return;
            }


            int minBuildHeight = QuartPos.fromBlock(chunk.getMinBuildHeight());
            int maxHeight = minBuildHeight + QuartPos.fromBlock(chunk.getHeight()) - 1;
            int dummyY = Mth.clamp(i3, minBuildHeight, maxHeight);
            int sectionIndex = chunk.getSectionIndex(QuartPos.toBlock(dummyY));
            ((PalettedContainer<Holder<Biome>>) chunk.getSections()[sectionIndex].getBiomes()).set(l2 & 3, dummyY & 3, j3 & 3, biomeHack.get());

            chunk.setUnsaved(true);
        } else {
            ModSet.Info("Tried changing biome at non-existing chunk for position " + posIn);
        }
    }

    private static double getFiddledDistance(long pSeed, int pX, int pY, int pZ, double pXNoise, double pYNoise, double pZNoise) {
       //辅助方法
        long $$7 = LinearCongruentialGenerator.next(pSeed, (long)pX);
        $$7 = LinearCongruentialGenerator.next($$7, (long)pY);
        $$7 = LinearCongruentialGenerator.next($$7, (long)pZ);
        $$7 = LinearCongruentialGenerator.next($$7, (long)pX);
        $$7 = LinearCongruentialGenerator.next($$7, (long)pY);
        $$7 = LinearCongruentialGenerator.next($$7, (long)pZ);
        double d0 = getFiddle($$7);
        $$7 = LinearCongruentialGenerator.next($$7, pSeed);
        double d1 = getFiddle($$7);
        $$7 = LinearCongruentialGenerator.next($$7, pSeed);
        double d2 = getFiddle($$7);
        return Mth.square(pZNoise + d2) + Mth.square(pYNoise + d1) + Mth.square(pXNoise + d0);
    }

    private static double getFiddle(long pSeed) {//辅助方法
        double d0 = (double)Math.floorMod(pSeed >> 24, 1024) / 1024.0D;
        return (d0 - 0.5D) * 0.9D;
    }

    public static void updateChunkAfterBiomeChange(Level level, ChunkPos chunkPos) {
        //辅助方法
        LevelChunk chunkSafe = level.getChunkSource().getChunk(chunkPos.x, chunkPos.z, false);
        if (chunkSafe == null) {
            ModSet.Info("Chunk is null, failed to update chunk after biome change");
            return;
        }
        ((ServerChunkCache) level.getChunkSource()).chunkMap.getPlayers(chunkPos, false).forEach((player) -> {
            player.connection.send(new ClientboundLevelChunkWithLightPacket(chunkSafe, ((ServerChunkCache) level.getChunkSource()).getLightEngine(), null, null));
            PacketHandler.sendToPlayer(new UpdataBiomePaket(chunkPos.x,chunkPos.z),player);
        });
    }

    public  void ReplaceTree(ServerLevel level, BlockPos StartPos, Map<Block, Block> Replace_Map,Set<Block> Source_Target) {
        //主方法替换树 参数1服务端世界,参数2被检测的开始位置,替换映射表 原方块->目标方块,Source_Target 欲检测的方块
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(StartPos);
        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();
            BlockState currentState = level.getBlockState(currentPos);
            Block currentBlock = currentState.getBlock();
            if (isTreeBlock(currentBlock,Source_Target)) {
                level.setBlockAndUpdate(currentPos,Replace_Map.get(currentState.getBlock()).defaultBlockState());
                //level.removeBlock(currentPos, false);
                for (BlockPos neighborPos : getNeighborPositions(currentPos)) {
                    if (level.isLoaded(neighborPos)) {
                        queue.add(neighborPos);
                    }
                }
            }
        }
    }
    public static BlockPos[] getNeighborPositions(BlockPos pos) {//辅助方法
        return new BlockPos[]{
                pos.north(), pos.east(), pos.south(), pos.west(), pos.above(), pos.below()
        };
    }
    public  void RemoveTree(ServerLevel level, BlockPos StartPos,Set<Block> Source_Target) {
        //主方法替换树 参数1服务端世界,参数2被检测的开始位置,替换映射表 原方块->目标方块,Source_Target 欲检测的方块
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(StartPos);
        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();
            BlockState currentState = level.getBlockState(currentPos);
            Block currentBlock = currentState.getBlock();
            if (isTreeBlock(currentBlock,Source_Target)) {
               level.removeBlock(currentPos, false);
                for (BlockPos neighborPos : getNeighborPositions(currentPos)) {
                    if (level.isLoaded(neighborPos)) {
                        queue.add(neighborPos);
                    }
                }
            }
        }
    }
    private static boolean isTreeBlock(Block block, Set<Block> blocks) {//辅助方法
        return blocks.contains(block);
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
        return block.defaultBlockState().is(BlockTags.LEAVES)|| block.defaultBlockState().is(BlockTags.FLOWERS);
    }








}














