package mutantescape.level.event;

import mutantescape.client.render.entity.*;
import mutantescape.client.render.overlay.Stage;
import mutantescape.level.register.*;

import mutantescape.level.register.Particle.Factory.DamageParticleFactory;
import mutantescape.level.register.Particle.Provider.*;
import mutantescape.level.register.entity.*;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.Random;


public class ModEventBus {


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class AttributeEvent{
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(RegisterEntity.Sheep_Entity.get(), Sheep_Entity.createAttributes().build());
            event.put(RegisterEntity.Cow_Entity.get(), Cow_Entity.createAttributes().build());
            event.put(RegisterEntity.PIG_Entity.get(), Pig_Entity.createAttributes().build());
            event.put(RegisterEntity.IRON_GOLEM_Entity.get(), Iron_Golem_Entity.createAttributes().build());
            event.put(RegisterEntity.ZOMBIE_Entity.get(), Zombie_Entity.createAttributes().build());
            event.put(RegisterEntity.Villager_Entity.get(), villager_Entity.createAttributes().build());
            event.put(RegisterEntity.Spider_Entity.get(), Spider_Entity.createAttributes().build());
            event.put(RegisterEntity.Player_Entity.get(), Player_Entity.createAttributes().build());
            event.put(RegisterEntity.Catalog_Entity.get(), Catalog_Entity.createAttributes().build());



        }

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class RenderEvent{
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(RegisterEntity.Sheep_Entity.get(),  Sheep_Render::new);
            event.registerEntityRenderer(RegisterEntity.Cow_Entity.get(),  Cow_Render::new);
            event.registerEntityRenderer(RegisterEntity.PIG_Entity.get(),  Pig_Render::new);
            event.registerEntityRenderer(RegisterEntity.ZOMBIE_Entity.get(),  Zombie_Render::new);
            event.registerEntityRenderer(RegisterEntity.IRON_GOLEM_Entity.get(),  Iron_Golem_Render::new);
            event.registerEntityRenderer(RegisterEntity.Villager_Entity.get(),  villager_Render::new);
            event.registerEntityRenderer(RegisterEntity.Spider_Entity.get(),  Spider_Render::new);
            event.registerEntityRenderer(RegisterEntity.Player_Entity.get(),  Player_Render::new);
            event.registerEntityRenderer(RegisterEntity.Catalog_Entity.get(),  Catalog_Render::new);




        }



        @SubscribeEvent
        public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(VanillaGuiOverlay.TITLE_TEXT.id(), "overlay", Stage.OVERLAY);
        }
        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            //注册叠加层


           // event.registerLayerDefinition(Exaple_Render.EXAMPLE_ENTITY, IEntityModel::createBodyLayer);

        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {
            Stage.complete=true;

        }


        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onRegisterDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {


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
            event.registerSpriteSet(RegisterParticleTypes.BIOME_SCRAPE.get(), ScapeBiomeParticleProvider::provider);
            event.registerSpriteSet(RegisterParticleTypes.DamageNorber.get(), DamageParticleFactory::new);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_1.get(), Blood1insectParticle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_2.get(), Blood1Particle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_3.get(), Blood1waterinsectParticle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_4.get(), Blood1waterParticle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_5.get(), Blood2insectParticle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_6.get(), Blood2Particle::provider);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD.get(), BloodParticle.Provider::new);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_SPLAT.get(), BloodSplatParticle.Provider::new);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_FOG.get(), BloodFogParticle.Provider::new);
            event.registerSpriteSet(RegisterParticleTypes.BLOOD_SPLASH.get(), BloodSplashParticle.Provider::new);
           // event.registerSpriteSet(ParticleTypes.RAIN,BloodParticle.Provider::new);

           // event.registerSpriteSet(ParticleTypes.DRIPPING_WATER,BloodParticle.Provider::new);










        }


        //生物生成规则注册
        @SubscribeEvent
        public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {





        }




        @SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {

        }




    }






}
