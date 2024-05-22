package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.IkeySet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MEGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MutantEscape.MODID);
    public static final RegistryObject<CreativeModeTab> TAB1 = CREATIVE_MODE_TABS.register(IkeySet.ModKey("item_group"), () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> MEItem.ITEM_GROUP.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

            }).build());

}
