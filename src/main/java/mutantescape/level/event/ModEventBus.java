package mutantescape.level.event;

import mutantescape.client.render.entity.model.IEntityModel;
import mutantescape.client.render.entity.Exaple_Render;
import mutantescape.client.render.overlay.Stage;
import mutantescape.level.register.*;
import mutantescape.level.register.Particle.Factory.DamageParticleFactory;
import mutantescape.level.register.entity.Exple_entity;

import mutantescape.tools.ModSet;
import mutantescape.tools.Utils.BiomeUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;


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

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }


    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class common{
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerParticles(RegisterParticleProvidersEvent event) {

            event.registerSpriteSet(RegisterParticleTypes.DamageNorber.get(), DamageParticleFactory::new);
        }

        @SubscribeEvent
        public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {

        }

        @SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {

        }



    }


    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.getConditionContext();


    }
}
