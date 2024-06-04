package mutantescape;


import mutantescape.level.attributes.Attrubes;
import mutantescape.level.register.*;

import mutantescape.network.PacketHandler;
import net.minecraft.server.commands.DebugPathCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;


@Mod(MutantEscape.MODID)
public class MutantEscape {
    public static final String MODID = "mutantescape";


    public static final String RESOURCE_PREFIX = MODID + ":";
    public MutantEscape() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();;
        modEventBus.addListener(this::commonSetup);
        Attrubes.Regiister();
        RegisterEntity.ENTITYS.register(modEventBus);
        RegisterItem.ITEMS.register(modEventBus);
        RegisterEffect.EFFECTS.register(modEventBus);
        RegisterBlock.BLOCKS.register(modEventBus);
        RegisterGroup.CREATIVE_MODE_TABS.register(modEventBus);
        RegisterFeatures.FEATURES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Attrubes::RangeAttribute);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
        MixinBootstrap.init();//启用mixin
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("notch");//启用mixin
       // ModLoader.get().postEvent();

    }


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        DebugPathCommand.register(event.getDispatcher());
    }










}
