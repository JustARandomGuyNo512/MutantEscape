package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.blocks.*;
import mutantescape.tools.ModSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterBlock {
    public static final DeferredRegister<Block> BLOCKS=DeferredRegister.create(ForgeRegistries.BLOCKS, MutantEscape.MODID);

    public static final RegistryObject<ScapeGrassBlock> INFECTED_GRASS_BLOCK=BLOCKS.register(ModSet.ModKey("infected_grass_block"),
            ()->new ScapeGrassBlock(BlockBehaviour.Properties.of().randomTicks())

    );

    public static final RegistryObject<ScapeFallingBlock> INFECTED_FALLING_BLOCK=BLOCKS.register(ModSet.ModKey("INFECTED_FALLING_BLOCK"),
            ()->new ScapeFallingBlock(BlockBehaviour.Properties.of().randomTicks())

    );


    public static final RegistryObject<Block> INFECTED_IRON_BLOCK=BLOCKS.register(ModSet.ModKey("infected_iron_block"),
            ()->new Block(BlockBehaviour.Properties.of())
    );

    public static final RegistryObject<Block> INFECTED_STONE_BLOCK=BLOCKS.register(ModSet.ModKey("INFECTED_STONE_BLOCK"),
            ()->new Block(BlockBehaviour.Properties.of())
    );

    public static final RegistryObject<ScapeGrass> INFECTED_GRASS=BLOCKS.register(ModSet.ModKey("infected_grass"),
            ()->new ScapeGrass(BlockBehaviour.Properties.of())
    );

    public static final RegistryObject<ScapeGrass> INFECTED_FLOWER=BLOCKS.register(ModSet.ModKey("INFECTED_FLOWER"),
            ()->new ScapeGrass(BlockBehaviour.Properties.of())
    );




    public static final RegistryObject<ScapeRotatedPillarBlock> INFECTED_TREE1_BLOCK=BLOCKS.register(ModSet.ModKey("mutantescape_log"),
            ()->new ScapeRotatedPillarBlock(BlockBehaviour.Properties.of().randomTicks())
    );

    public static final RegistryObject<ScapeLaves> INFECTED_LEAVES=BLOCKS.register(ModSet.ModKey("INFECTED_LEAVES"),
            ()->new ScapeLaves(BlockBehaviour.Properties.of().randomTicks().isRedstoneConductor((bs, br, bp) -> false))
    );






}
