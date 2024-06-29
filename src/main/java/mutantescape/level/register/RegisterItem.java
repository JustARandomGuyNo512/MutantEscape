package mutantescape.level.register;

import mutantescape.MutantEscape;
import mutantescape.tools.ModSet;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterItem {
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, MutantEscape.MODID);
    public static final RegistryObject<Item> ITEM_GROUP=ITEMS.register(ModSet.ModKey("mutantEscape"),()->new Item(new Item.Properties()));
    public static final RegistryObject<BlockItem> INFECTED_GRASS_BLOCK_ITEM=ITEMS.register(ModSet.ModKey("infected_grass_block"),
            ()->new BlockItem(RegisterBlock.INFECTED_GRASS_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<BlockItem> INFECTED_IRON_BLOCK_ITEM=ITEMS.register(ModSet.ModKey("infected_iron_block"),
            ()->new BlockItem(RegisterBlock.INFECTED_IRON_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<BlockItem> INFECTED_GRASS_ITEM=ITEMS.register(ModSet.ModKey("infected_grass"),
            ()->new BlockItem(RegisterBlock.INFECTED_GRASS.get(),new Item.Properties()));
    public static final RegistryObject<BlockItem> INFECTED_FLOWER_ITEM=ITEMS.register(ModSet.ModKey("INFECTED_FLOWER"),
            ()->new BlockItem(RegisterBlock.INFECTED_FLOWER.get(),new Item.Properties()));

    public static final RegistryObject<BlockItem> INFECTED_TREE1_ITEM=ITEMS.register(ModSet.ModKey("mutantescape_log"),
            ()->new BlockItem(RegisterBlock.INFECTED_TREE1_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<BlockItem> INFECTED_LEVES_ITEM=ITEMS.register(ModSet.ModKey("INFECTED_LEAVES"),
            ()->new BlockItem(RegisterBlock.INFECTED_LEAVES.get(),new Item.Properties()));

    public static final RegistryObject<BlockItem> INFECTED_FALLING_BLOCK_ITEM=ITEMS.register(ModSet.ModKey("INFECTED_FALLING_BLOCK"),
            ()->new BlockItem(RegisterBlock.INFECTED_FALLING_BLOCK.get(),new Item.Properties()));


    public static final RegistryObject<BlockItem> INFECTED_STONE_BLOCK_ITEM=ITEMS.register(ModSet.ModKey("INFECTED_STONE_BLOCK"),
            ()->new BlockItem(RegisterBlock.INFECTED_STONE_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> ITEM_VINE=ITEMS.register(ModSet.ModKey("ITEM_VINE"),()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_ROTTING_FLRSH=ITEMS.register(ModSet.ModKey("ITEM_ROTTING_FLRSH"),()->new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(2.5f)
            .build())));



}
