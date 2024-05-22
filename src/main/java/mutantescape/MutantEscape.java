package mutantescape;

import mutantescape.client.config.MEConfig;
import mutantescape.level.register.EntityRegister;
import mutantescape.level.register.MEGroup;
import mutantescape.level.register.MEItem;
import mutantescape.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MutantEscape.MODID)
public class MutantEscape {
    public static final String MODID = "mutantescape";

    public MutantEscape() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MEConfig.register();
        modEventBus.register(MEConfig.class);
        EntityRegister.ENTITYS.register(modEventBus);
        MEItem.ITEMS.register(modEventBus);
        MEGroup.CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }
}
