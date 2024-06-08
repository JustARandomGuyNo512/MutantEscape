package mutantescape.tools.Utils;

import mutantescape.level.register.RegisterBlock;
import mutantescape.level.register.blocks.ScapeDoublePlantBlock;
import mutantescape.level.register.structure.Structure;
import mutantescape.network.PacketHandler;
import mutantescape.network.s2c.UpdataBiomePaket;
import mutantescape.tools.ModSet;
import net.minecraft.core.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.LinearCongruentialGenerator;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.*;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class BiomeUtils{

    private final Set<Block> LIST_BLOCKS = new HashSet<>();
    private static final Set<Block> TREE_BLOCKS = new HashSet<>();
    private final Map<Block, Block> ScapeBlock = new HashMap<>();
    private static final Set<BlockPos> APositions = Collections.synchronizedSet(new HashSet<>());
    private static final ExecutorService executor = Executors.newCachedThreadPool();


    private static final AtomicInteger time = new AtomicInteger(0);
    private static int SpreadValue;
    private static final int Timeout = 20;
    private int ApoBlockSize = 1000;
    private int ApoBlockTSize = 2000;
    private static int Speed = 1000;
    private static ServerLevel level;

    public ServerLevel getLevel() {
        return level;
    }
    public static final BiomeUtils BiomesUtils=new BiomeUtils();
    static {

        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (level != null) {
                    time.incrementAndGet();
                } else {
                    APositions.clear();
                    time.set(0);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, Speed);
    }

    private void addBlackList() {
        LIST_BLOCKS.add(Blocks.WATER);
        LIST_BLOCKS.add(Blocks.BEDROCK);
        LIST_BLOCKS.add(Blocks.AIR);
        LIST_BLOCKS.add(Blocks.LAVA);

    }

    public void loadBlockList() {
        if (!LIST_BLOCKS.contains(Blocks.AIR)) {
            addBlackList();
        } else if (LIST_BLOCKS.contains(Blocks.AIR) && !LIST_BLOCKS.contains(RegisterBlock.INFECTED_GRASS_BLOCK.get())) {
            ScapeBlock.put(Blocks.IRON_ORE, RegisterBlock.INFECTED_IRON_BLOCK.get());
            ScapeBlock.put(Blocks.STONE, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.GRASS_BLOCK, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.DIRT, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.GRASS, RegisterBlock.INFECTED_GRASS.get());
            ScapeBlock.put(Blocks.TALL_GRASS, RegisterBlock.INFECTED_DOUBLEPLANT.get());

            ScapeBlock.put(Blocks.OAK_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());
            ScapeBlock.put(Blocks.SPRUCE_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());
            ScapeBlock.put(Blocks.BIRCH_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());
            ScapeBlock.put(Blocks.JUNGLE_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());
            ScapeBlock.put(Blocks.ACACIA_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());
            ScapeBlock.put(Blocks.DARK_OAK_LOG, RegisterBlock.INFECTED_TREE1_BLOCK.get());

            TREE_BLOCKS.add(Blocks.OAK_LOG);
            TREE_BLOCKS.add(Blocks.SPRUCE_LOG);
            TREE_BLOCKS.add(Blocks.BIRCH_LOG);
            TREE_BLOCKS.add(Blocks.JUNGLE_LOG);
            TREE_BLOCKS.add(Blocks.ACACIA_LOG);
            TREE_BLOCKS.add(Blocks.DARK_OAK_LOG);
            TREE_BLOCKS.add(Blocks.OAK_LEAVES);
            TREE_BLOCKS.add(Blocks.SPRUCE_LEAVES);
            TREE_BLOCKS.add(Blocks.BIRCH_LEAVES);
            TREE_BLOCKS.add(Blocks.JUNGLE_LEAVES);
            TREE_BLOCKS.add(Blocks.ACACIA_LEAVES);
            TREE_BLOCKS.add(Blocks.DARK_OAK_LEAVES);

            ScapeBlock.put(Blocks.GRANITE, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.DIORITE, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.ANDESITE, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.COBBLESTONE, RegisterBlock.INFECTED_GRASS_BLOCK.get());
            ScapeBlock.put(Blocks.STONE_SLAB, RegisterBlock.INFECTED_GRASS_BLOCK.get());

            ScapeBlock.put(Blocks.OAK_LEAVES, RegisterBlock.INFECTED_LEAVES.get());
            ScapeBlock.put(Blocks.SPRUCE_LEAVES, RegisterBlock.INFECTED_LEAVES.get());
            ScapeBlock.put(Blocks.BIRCH_LEAVES, RegisterBlock.INFECTED_LEAVES.get());
            ScapeBlock.put(Blocks.JUNGLE_LEAVES, RegisterBlock.INFECTED_LEAVES.get());
            ScapeBlock.put(Blocks.ACACIA_LEAVES, RegisterBlock.INFECTED_LEAVES.get());
            ScapeBlock.put(Blocks.DARK_OAK_LEAVES, RegisterBlock.INFECTED_LEAVES.get());




            // Other blocks...

            LIST_BLOCKS.add(RegisterBlock.INFECTED_GRASS_BLOCK.get());
            LIST_BLOCKS.add(RegisterBlock.INFECTED_IRON_BLOCK.get());
            LIST_BLOCKS.add(RegisterBlock.INFECTED_GRASS.get());
            LIST_BLOCKS.add(RegisterBlock.INFECTED_DOUBLEPLANT.get());
            LIST_BLOCKS.add(RegisterBlock.INFECTED_TREE1_BLOCK.get());
            LIST_BLOCKS.add(RegisterBlock.INFECTED_LEAVES.get());

        }
    }

    public void setLevel(ServerLevel level) {
        BiomeUtils.level = level;
    }

    public static int getSpreadValue() {
        return SpreadValue;
    }

    public static void setSpreadValue(int SprValue) {
        SpreadValue = SprValue;
    }

    public BiomeUtils() {

    }

    public void spreadBlock(BlockPos blockPos) {
        loadBlockList();
        BlockPos randomPos = blockPos.relative(Direction.getRandom(RandomSource.createNewThreadLocalInstance()));
        Block randomBlock = level.getBlockState(randomPos).getBlock();
        if (!LIST_BLOCKS.contains(randomBlock) && ScapeBlock.containsKey(randomBlock)) {
            APositions.add(randomPos);
            if (APositions.size() >= this.ApoBlockSize || time.get() >= Timeout && APositions.size()<=ApoBlockTSize) {
                HashSet<BlockPos> setPos = new HashSet<>(APositions);
                asyncMethod(this.getLevel(), setPos);
                time.set(0);
                APositions.clear();
            }
        }
    }

    private static void spread(ServerLevel level, Block toSpread, BlockPos offSetPos) {
        BlockState stateToPlace = toSpread.defaultBlockState();
        if (toSpread == RegisterBlock.INFECTED_DOUBLEPLANT.get()) {
            level.setBlock(offSetPos, stateToPlace.setValue(ScapeDoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 16);
            level.setBlock(offSetPos.above(), stateToPlace.setValue(ScapeDoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 16);
        }  else {
            level.setBlockAndUpdate(offSetPos, stateToPlace);
        }
        level.getChunkSource().chunkMap.getPlayers(new ChunkPos(offSetPos), false).forEach(player -> {
            player.connection.send(new ClientboundBlockUpdatePacket(level, offSetPos));
            if (toSpread == RegisterBlock.INFECTED_DOUBLEPLANT.get()) {
                player.connection.send(new ClientboundBlockUpdatePacket(level, offSetPos.above()));
            }
        });
        SpreadValue++;
    }


    public static void removeTree(ServerLevel level, BlockPos startPos) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(startPos);
        while (!queue.isEmpty()) {
            BlockPos currentPos = queue.poll();
            BlockState currentState = level.getBlockState(currentPos);
            Block currentBlock = currentState.getBlock();
            if (isTreeBlock(currentBlock)) {
                level.removeBlock(currentPos, false);
                for (BlockPos neighborPos : getNeighborPositions(currentPos)) {
                    if (level.isLoaded(neighborPos)) {
                        queue.add(neighborPos);
                    }
                }
            }
        }
    }

    // 检查一个块是否是树叶或树干
    private static boolean isTreeBlock(Block block) {
       return TREE_BLOCKS.contains(block);
    }

    // 获取一个块周围的六个方向的相邻块的位置
    private static BlockPos[] getNeighborPositions(BlockPos pos) {
        return new BlockPos[]{
                pos.north(), pos.east(), pos.south(), pos.west(), pos.above(), pos.below()
        };
    }








    private void asyncMethod(ServerLevel level, Set<BlockPos> positions) {
        CompletableFuture.supplyAsync(() -> {
            positions.parallelStream().forEach(
                    pos -> {
                        level.getServer().execute(() -> {
                            Block targetBlock = this.ScapeBlock.get(level.getBlockState(pos).getBlock().defaultBlockState().getBlock());
                            if (targetBlock != null) {
                                if (isNaturalTree(level,pos)) {
                                    removeTree(level, pos);
                                    Structure.generateStructure(level,pos,"trees");
                                }else {
                                    spread(level, targetBlock, pos);
                                }
                                if (ModSet.isSurface(level, pos.above())) {
                                    level.sendParticles(ParticleTypes.SCULK_SOUL, pos.getX(), pos.getY(), pos.getZ(), 1, 0, 0, 0, 0.5);
                                    setBiome(level, pos.above(), Biomes.BASALT_DELTAS);
                                }
                            }
                        });
                    }
            );
            return positions;
        }, executor);
    }


    public static boolean isNaturalTree(ServerLevel level, BlockPos startPos) {
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

        // 判断树的结构是否符合自然生成的树
        return logCount > 0 && leavesCount > 0 && leavesCount / logCount >= 1;
    }







    public static void setBiome(ServerLevel serverLevel, BlockPos blockPos, ResourceKey<Biome> biome) {
        setPosBiome(serverLevel,blockPos,biome);
        updateChunkAfterBiomeChange(serverLevel, new ChunkPos(blockPos));
    }



    public static void setPosBiome(ServerLevel level, BlockPos posIn, ResourceKey<Biome> biome) {
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
            ModSet.Debug("Tried changing biome at non-existing chunk for position " + posIn,2);
        }
    }

    private static double getFiddledDistance(long pSeed, int pX, int pY, int pZ, double pXNoise, double pYNoise, double pZNoise) {
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

    private static double getFiddle(long pSeed) {
        double d0 = (double)Math.floorMod(pSeed >> 24, 1024) / 1024.0D;
        return (d0 - 0.5D) * 0.9D;
    }

    public static void updateChunkAfterBiomeChange(Level level, ChunkPos chunkPos) {
        LevelChunk chunkSafe = level.getChunkSource().getChunk(chunkPos.x, chunkPos.z, false);
        if (chunkSafe == null) {
           ModSet.Debug("Chunk is null, failed to update chunk after biome change",2);
            return;
        }
        ((ServerChunkCache) level.getChunkSource()).chunkMap.getPlayers(chunkPos, false).forEach((player) -> {
            player.connection.send(new ClientboundLevelChunkWithLightPacket(chunkSafe, ((ServerChunkCache) level.getChunkSource()).getLightEngine(), null, null));
            PacketHandler.sendToPlayer(new UpdataBiomePaket(chunkPos.x,chunkPos.z),player);
        });
    }







}
