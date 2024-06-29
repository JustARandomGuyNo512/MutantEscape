package mutantescape.level.register.blocks;

import mutantescape.level.function.BiomeFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

public class ScapeLaves extends LeavesBlock {

    public ScapeLaves(Properties pProperties) {
        super(pProperties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().instabreak().emissiveRendering((bs, br, bp) -> true)
                .lightLevel((state) -> 15)
                .ignitedByLava().pushReaction(PushReaction.DESTROY));
    }

    @Override
    public void randomTick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (pLevel.isAreaLoaded(pPos, 1)){
         BiomeFunction.addPos(pLevel, pPos);
        }
    }



}
