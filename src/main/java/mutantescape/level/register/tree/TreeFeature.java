package mutantescape.level.register.tree;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;


public class TreeFeature extends Feature<NoneFeatureConfiguration> {
    ResourceLocation TreeResource;



    public TreeFeature(Codec<NoneFeatureConfiguration> pCodec,ResourceLocation treeresource) {

        super(pCodec);
        this.TreeResource=treeresource;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel level = pContext.level();
        BlockPos pos = pContext.origin();
        StructureTemplateManager structuretemplatemanager = level.getLevel().getServer().getStructureManager();
        StructureTemplate template=structuretemplatemanager.getOrCreate(TreeResource);
        StructurePlaceSettings settings = new StructurePlaceSettings().setIgnoreEntities(false);
        template.placeInWorld(level, pos, pos, settings, level.getRandom(), 3);
        return true;
    }
}
