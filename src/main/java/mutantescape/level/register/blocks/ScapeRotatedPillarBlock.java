package mutantescape.level.register.blocks;

import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import static mutantescape.tools.Utils.BiomeUtils.*;
public class ScapeRotatedPillarBlock extends RotatedPillarBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public ScapeRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
       pBuilder.add(AXIS);
   }


    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        BiomesUtils.setLevel(pLevel);
        BiomesUtils.spreadBlock(pPos);
    }


}
