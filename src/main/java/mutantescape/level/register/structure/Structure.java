package mutantescape.level.register.structure;

import mutantescape.MutantEscape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class Structure {
    public static void generateStructure(ServerLevel world, BlockPos pos, String structureName) {
        StructureTemplate structure = world.getStructureManager().getOrCreate(new ResourceLocation(MutantEscape.MODID, structureName));
        StructurePlaceSettings settings = new StructurePlaceSettings().setIgnoreEntities(false);
        BoundingBox boundingBox = structure.getBoundingBox(settings,pos);
        structure.placeInWorld(world, new BlockPos(boundingBox.getCenter().getX(),boundingBox.getCenter().getY(),boundingBox.getCenter().getZ()),pos, settings, world.getRandom(), 3);
    }


}
