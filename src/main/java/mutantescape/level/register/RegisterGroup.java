package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.ModSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MutantEscape.MODID);
    public static final RegistryObject<CreativeModeTab> TAB1 = CREATIVE_MODE_TABS.register(ModSet.ModKey("item_group"), () -> CreativeModeTab.builder()
            .title(Component.keybind("ItemGroup."+MutantEscape.MODID+"."+ModSet.ModKey("Item")))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> RegisterItem.ITEM_GROUP.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
             output.accept(RegisterItem.INFECTED_GRASS_BLOCK_ITEM.get());
             output.accept(RegisterItem.INFECTED_IRON_BLOCK_ITEM.get());
             output.accept(RegisterItem.INFECTED_GRASS_ITEM.get());
             output.accept(RegisterItem.INFECTED_TREE1_ITEM.get());
             output.accept(RegisterItem.INFECTED_LEVES_ITEM.get());
             output.accept(RegisterItem.INFECTED_FLOWER_ITEM.get());
             output.accept(RegisterItem.INFECTED_FALLING_BLOCK_ITEM.get());
             output.accept(RegisterItem.INFECTED_STONE_BLOCK_ITEM.get());
             output.accept(RegisterItem.ITEM_VINE.get());
             output.accept(RegisterItem.ITEM_ROTTING_FLRSH.get());




            }).build());

}
