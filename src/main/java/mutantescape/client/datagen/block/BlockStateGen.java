package mutantescape.client.datagen.block;


import mutantescape.MutantEscape;
import mutantescape.level.register.RegisterBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGen extends BlockStateProvider {
    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MutantEscape.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_FALLING_BLOCK.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_FALLING_BLOCK.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_IRON_BLOCK.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_IRON_BLOCK.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_GRASS.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_GRASS.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_FLOWER.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_FLOWER.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_STONE_BLOCK.get()),cubeAll(BlockIsNull(RegisterBlock.INFECTED_STONE_BLOCK.get())));

        LeavesBlock(BlockIsNull(RegisterBlock.INFECTED_LEAVES.get()), "mutantescape_infected_leaves");

        formSideBlock(BlockIsNull(RegisterBlock.INFECTED_GRASS_BLOCK.get()), modLoc("block/mutantescape_infected_grass_block_side"), modLoc("block/mutantescape_infected_grass_block_top"), modLoc("block/mutantescape_infected_grass_block_buttom"),modLoc("block/mutantescape_infected_grass_block_top"));


        //this.DoublePlantBlock(RegisterBlock.INFECTED_DOUBLEPLANT.get(), "mutantescape_infected_doubleplant", "mutantescape_infected_doubleplant_bottom");
        logBlock(RegisterBlock.INFECTED_TREE1_BLOCK.get(), modLoc("block/mutantescape_log_side"), modLoc("block/mutantescape_log_top"));



        //this.propertyBlock(ModBlocks.LAMP_BLOCK.get());
    }


    private Block BlockIsNull(Block block) {
        //出于特殊原因老是检测到空值报错所以判断之后输出
        if (block != null)
            return block;
        return Blocks.AIR;
    }


    public void DoublePlantBlock(Block block, String upperTexture, String lowerTexture) {
        var block_upper = models().getBuilder(upperTexture)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/cross")))
                .texture("cross", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + upperTexture))
                .texture("particle", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + upperTexture))
                .renderType("cutout");

        var block_lower = models().getBuilder(lowerTexture)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/cross")))
                .texture("cross", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + lowerTexture))
                .texture("particle", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + lowerTexture))
                .renderType("cutout");

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(block_upper).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(block_lower).addModel();

        simpleBlockItem(block, block_lower);
    }

    public void logBlock(Block block, ResourceLocation side, ResourceLocation end) {
        //原木类别
        ModelFile log = models().cubeColumn(BlockModelGen.getBlockPath(block).getPath(), side, end);
        ModelFile logHorizontal = models().cubeColumnHorizontal(BlockModelGen.getBlockPath(block).getPath(), side, end);

        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.forAllStates(state -> {
            Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
            int xRot = axis == Direction.Axis.X ? 90 : 0;
            int yRot = axis == Direction.Axis.Z ? 90 : 0;

            return ConfiguredModel.builder()
                    .modelFile(axis == Direction.Axis.Y ? log : logHorizontal)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        });

        itemModels().withExistingParent(BlockModelGen.getBlockPath(block).getPath(), modLoc("block/" + BlockModelGen.getBlockPath(block).getPath()));
    }
    public void LeavesBlock(Block block, String texture) {
        //树叶类型
        var leavesBlock = models().getBuilder(texture)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/cube_all")))
                .texture("all", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + texture))
                .texture("particle", new ResourceLocation(MutantEscape.MODID, ModelProvider.BLOCK_FOLDER + "/" + texture))
                .renderType("cutout");

        getVariantBuilder(block)
                .forAllStates(state ->
                        ConfiguredModel.builder()
                                .modelFile(leavesBlock)
                                .build()
                );

        simpleBlockItem(block, leavesBlock);
    }
    public void CustomBlock(Block block, ResourceLocation top, ResourceLocation bottom, ResourceLocation north, ResourceLocation south, ResourceLocation west, ResourceLocation east) {
        //普通方块类别
        ModelFile customLog = models().withExistingParent(BlockModelGen.getBlockPath(block).getPath(), "cube")
                .texture("particle", north)
                .texture("down", bottom)
                .texture("up", top)
                .texture("north", north)
                .texture("south", south)
                .texture("west", west)
                .texture("east", east);

        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.forAllStates(state -> {
            Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
            int xRot = axis == Direction.Axis.X ? 90 : (axis == Direction.Axis.Z ? 90 : 0);
            int yRot = axis == Direction.Axis.Z ? 90 : 0;
            return ConfiguredModel.builder()
                    .modelFile(customLog)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        });

        itemModels().withExistingParent(BlockModelGen.getBlockPath(block).getPath(), modLoc("block/" + BlockModelGen.getBlockPath(block).getPath()));
    }
    public void formSideBlock(Block block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, ResourceLocation snowyTop) {
        // 定义一个普通的方块模型（没有雪覆盖）
        ModelFile customBlock = models().withExistingParent(BlockModelGen.getBlockPath(block).getPath(), "cube")
                .texture("particle", side)
                .texture("down", bottom)
                .texture("up", top)
                .texture("north", side)
                .texture("south", side)
                .texture("west", side)
                .texture("east", side);

        // 定义一个有雪覆盖的方块模型
        ModelFile customBlockSnowy = models().withExistingParent(BlockModelGen.getBlockPath(block).getPath() + "_snowy", "cube")
                .texture("particle", side)
                .texture("down", bottom)
                .texture("up", snowyTop)
                .texture("north", side)
                .texture("south", side)
                .texture("west", side)
                .texture("east", side);

        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().with(BlockStateProperties.SNOWY, false).addModels(new ConfiguredModel(customBlock));
        builder.partialState().with(BlockStateProperties.SNOWY, true).addModels(new ConfiguredModel(customBlockSnowy));

        // 生成物品模型，默认使用没有雪覆盖的模型
        itemModels().withExistingParent(BlockModelGen.getBlockPath(block).getPath(), modLoc("block/" + BlockModelGen.getBlockPath(block).getPath()));
    }





}