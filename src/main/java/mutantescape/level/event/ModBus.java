package mutantescape.level.event;

import mutantescape.client.render.hud.StageRender;
import mutantescape.level.register.EntityRegister;
import mutantescape.level.register.Exaple_Render;
import mutantescape.level.register.Exple_entity;
import mutantescape.level.register.IEntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBus {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(EntityRegister.Master_Catalog.get(), Exple_entity.createAttributes().build());


    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.Master_Catalog.get(),  Exaple_Render::new);
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {

    }

    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {

    }

//    @SubscribeEvent
//    public static void onMobSpawnFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
//
//    }

    @SubscribeEvent
    public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.TITLE_TEXT.id(), "overlay", StageRender.OVERLAY);
    }

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        StageRender.complete=true;
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(Exaple_Render.EXAMPLE_ENTITY, IEntityModel::createBodyLayer);
    }
}
