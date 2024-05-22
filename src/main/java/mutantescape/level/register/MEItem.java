package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.IkeySet;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MEItem {
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, MutantEscape.MODID);
    public static final RegistryObject<Item> ITEM_GROUP=ITEMS.register(IkeySet.ModKey("mutantEscape"),()->new Item(new Item.Properties()));





}
