package mutantescape.tools.Utils;

import com.sun.jna.platform.unix.X11;
import mutantescape.MutantEscape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class FeatureUtils {
    public static void SetStructure(ServerLevel world, BlockPos pos, String StructureName,boolean IgnoreEntities) {
        StructureTemplate structure = world.getStructureManager().getOrCreate(new ResourceLocation(MutantEscape.MODID, StructureName));
        StructurePlaceSettings settings = new StructurePlaceSettings().setIgnoreEntities(IgnoreEntities);
        Vec3i Sizes = structure.getSize();
        BoundingBox boundingBox = structure.getBoundingBox(settings,pos);
        structure.placeInWorld(world, new BlockPos(boundingBox.getCenter().getX(),boundingBox.getCenter().getY(),boundingBox.getCenter().getZ()),pos, settings, world.getRandom(), 3);
    }

}
