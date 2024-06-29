package mutantescape.client.datagen.block;

import mutantescape.MutantEscape;
import mutantescape.level.register.RegisterBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlockModelGen extends BlockModelProvider {
    public BlockModelGen(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, MutantEscape.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        cubeAll(getBlockPath(RegisterBlock.INFECTED_IRON_BLOCK.get()).getPath(),modLoc("block/mutantescape_infected_iron_block"));
        cubeAll(getBlockPath(RegisterBlock.INFECTED_FALLING_BLOCK.get()).getPath(),modLoc("block/mutantescape_infected_falling_block"));


        CrossBlock(getBlockPath(RegisterBlock.INFECTED_GRASS.get()).getPath(), modLoc("block/mutantescape_infected_grass"));
        CrossBlock(getBlockPath(RegisterBlock.INFECTED_FLOWER.get()).getPath(), modLoc("block/mutantescape_infected_flower"));


    }


    public static ResourceLocation getBlockPath(Block block){
        @Nullable ResourceLocation IObject = ForgeRegistries.BLOCKS.getKey(block);
        return Objects.requireNonNullElseGet(IObject, () -> new ResourceLocation(""));
    }

    private void CrossBlock(String name, ResourceLocation texture) {
        //草类
        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(mcLoc("block/cross")))
                .texture("cross", texture)
                .texture("particle", texture)
                .renderType("cutout")
        ;

    }



}
