package mutantescape.level.register.blocks;

import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

import static mutantescape.tools.Utils.BiomeUtils.*;

public class ScapeGrassBlock extends Block {

    public ScapeGrassBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        BiomesUtils.setLevel(pLevel);
        BiomesUtils.spreadBlock(pPos);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, net.minecraft.core.Direction facing, IPlantable plantable) {
        return true;
    }
}
