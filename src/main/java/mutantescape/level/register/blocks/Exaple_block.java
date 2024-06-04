package mutantescape.level.register.blocks;

import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Exaple_block extends Block {
    private final BiomeUtils BiomesUtils=new BiomeUtils();
    public Exaple_block(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.BiomesUtils.loadBlockList();
        this.BiomesUtils.setLevel(pLevel);
        this.BiomesUtils.SpreadBlock(pState.getBlock(),pPos);




        super.randomTick(pState, pLevel, pPos, pRandom);
    }
}
