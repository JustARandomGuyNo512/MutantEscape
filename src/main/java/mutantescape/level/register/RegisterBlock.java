package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.level.register.blocks.Exaple_block;
import mutantescape.tools.ModSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterBlock {
    public static final DeferredRegister<Block> BLOCKS=DeferredRegister.create(ForgeRegistries.BLOCKS, MutantEscape.MODID);

    public static final RegistryObject<Exaple_block> INFECTED_GRASS_BLOCK=BLOCKS.register(ModSet.ModKey("infected_grass_block"),
            ()->new Exaple_block(BlockBehaviour.Properties.of().randomTicks())

    );

}
