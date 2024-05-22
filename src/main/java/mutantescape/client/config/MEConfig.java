package mutantescape.client.config;

import mutantescape.MutantEscape;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = MutantEscape.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MEConfig {
    public static final ForgeConfigSpec common;
    public static final ForgeConfigSpec client;
    public static final ForgeConfigSpec.ConfigValue<Double> StageValue;
    public static final ForgeConfigSpec.ConfigValue<Double> ReasonValue;
    public static final ForgeConfigSpec.ConfigValue<Integer> Protection_Period;
    public static Double stage_value,reason_value;
    public static Integer protection_period;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("game");
        StageValue = builder.comment("游戏默认初始演化阶段值.").define("default.stage", 100.0);
        Protection_Period= builder.comment("游戏默认初始安全期限值.").define("default.protection_period", 7);
        ReasonValue = builder.comment("游戏默认初始理智值.").define("default.reason_value", 100.0);


        common = builder.build();
        builder = new ForgeConfigSpec.Builder();







        client=builder.build();
    }

    public static void register() {
        ModLoadingContext context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, common);
        context.registerConfig(ModConfig.Type.CLIENT, client);
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        stage_value=StageValue.get();
        protection_period=Protection_Period.get();
        reason_value=ReasonValue.get();
        ModConfig config = event.getConfig();
        if (config.getModId().equals(MutantEscape.MODID)) {
            switch (config.getType()) {
                case COMMON -> {

                }
                case CLIENT -> {

                }


            }


        }
    }



}
