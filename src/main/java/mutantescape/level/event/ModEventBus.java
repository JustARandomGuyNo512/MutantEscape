package mutantescape.level.event;

import mutantescape.client.render.entity.model.IEntityModel;
import mutantescape.client.render.entity.Exaple_Render;
import mutantescape.client.render.hud.Stage;
import mutantescape.level.register.*;
import mutantescape.level.register.entity.Exple_entity;
import mutantescape.level.register.event.BiomeGeneration;

import mutantescape.tools.ModSet;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterStructureConversionsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.RegisterEvent;


public class ModEventBus {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class AttributeEvent{
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(RegisterEntity.Master_Catalog.get(), Exple_entity.createAttributes().build());
        }

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class RenderEvent{
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(RegisterEntity.Master_Catalog.get(),  Exaple_Render::new);
        }
        @SubscribeEvent
        public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(VanillaGuiOverlay.TITLE_TEXT.id(), "overlay", Stage.OVERLAY);
        }
        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(IEntityModel.EXAMPLE_ENTITY, IEntityModel::createBodyLayer);

        }

        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {
            Stage.complete=true;
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class common{
        @SubscribeEvent
        public static void registerParticles(RegisterParticleProvidersEvent event) {

        }

        @SubscribeEvent
        public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {

        }



    }






}
