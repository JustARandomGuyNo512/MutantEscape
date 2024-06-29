package mutantescape.tools;

import com.mojang.logging.LogUtils;
import mutantescape.MutantEscape;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;

public class ModSet {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static String ModKey(String key){
        return MutantEscape.MODID+"_" +key.toLowerCase().replace(" ","_");
    }

    public static void Error(String format, Object... args) {
        LOGGER.error(String.format(format, args));
    }
    public static void Warn(String format, Object... args) {
        LOGGER.warn(String.format(format, args));
    }

    public static void Info(String format, Object... args) {
        LOGGER.info(String.format(format, args));
    }

    private boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }









}
