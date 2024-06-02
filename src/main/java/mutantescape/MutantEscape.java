package mutantescape;


import mutantescape.level.register.*;
import mutantescape.network.PacketHandler;
import mutantescape.tools.BiomeUtils;
import mutantescape.tools.Reflection;
import net.minecraft.server.commands.DebugPathCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MutantEscape.MODID)
public class MutantEscape {
    public static final String MODID = "mutantescape";


    public static final String RESOURCE_PREFIX = MODID + ":";
    public MutantEscape() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Reflection.init();
        modEventBus.addListener(this::commonSetup);
        RegisterEntity.ENTITYS.register(modEventBus);
        RegisterItem.ITEMS.register(modEventBus);
        RegisterEffect.EFFECTS.register(modEventBus);
        RegisterBlock.BLOCKS.register(modEventBus);
        RegisterGroup.CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PacketHandler.register();

       // ModLoader.get().postEvent();

    }


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        DebugPathCommand.register(event.getDispatcher());
    }
}
