package mutantescape.client.datagen.item;

import mutantescape.MutantEscape;
import mutantescape.level.register.RegisterItem;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemGen extends ItemModelProvider {
    public ItemGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MutantEscape.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(RegisterItem.ITEM_GROUP.get());
        this.basicItem(RegisterItem.ITEM_VINE.get());
        this.basicItem(RegisterItem.ITEM_ROTTING_FLRSH.get());
    }
}
