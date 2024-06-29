package mutantescape.level.register.blocks;

import mutantescape.level.function.BiomeFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;


public class ScapeFallingBlock extends FallingBlock {
    public ScapeFallingBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isAreaLoaded(pPos, 1)){
            BiomeFunction.addPos(pLevel, pPos);
        }
    }
}
