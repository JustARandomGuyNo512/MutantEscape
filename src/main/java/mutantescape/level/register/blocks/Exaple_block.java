package mutantescape.level.register.blocks;

import mutantescape.level.register.RegisterBlock;
import mutantescape.tools.BiomeUtils;
import mutantescape.tools.ModSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Exaple_block extends Block {
    public Exaple_block(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        //BiomeUtils.spreadBlock(RegisterBlock.INFECTED_GRASS_BLOCK.get(),pLevel,pPos );
    }


}
