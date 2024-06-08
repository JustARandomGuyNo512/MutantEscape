package mutantescape.datagen.block;


import mutantescape.MutantEscape;
import mutantescape.level.register.RegisterBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
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
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_GRASS_BLOCK.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_GRASS_BLOCK.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_IRON_BLOCK.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_IRON_BLOCK.get())));
        this.simpleBlockWithItem(BlockIsNull(RegisterBlock.INFECTED_GRASS.get()), cubeAll(BlockIsNull(RegisterBlock.INFECTED_GRASS.get())));
        propertyLeavesBlock(BlockIsNull(RegisterBlock.INFECTED_LEAVES.get()), "mutantescape_infected_leaves");



        this.propertyDoublePlantBlock(RegisterBlock.INFECTED_DOUBLEPLANT.get(), "mutantescape_infected_doubleplant", "mutantescape_infected_doubleplant_bottom");
        axisBlock(RegisterBlock.INFECTED_TREE1_BLOCK.get(), modLoc("block/mutantescape_log_side"), modLoc("block/mutantescape_log_top"));
        //this.propertyBlock(ModBlocks.LAMP_BLOCK.get());
    }


    private Block BlockIsNull(Block block) {
        //出于特殊原因老是检测到空值报错所以判断之后输出
        if (block != null)
            return block;
        return Blocks.AIR;
    }


    public void propertyDoublePlantBlock(Block block, String upperTexture, String lowerTexture) {
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

    public void axisBlock(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) {
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



    public void propertyLeavesBlock(Block block, String texture) {
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





}