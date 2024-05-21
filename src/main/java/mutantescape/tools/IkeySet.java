package mutantescape.tools;

import com.mojang.logging.LogUtils;
import mutantescape.MutantEscape;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

public class IkeySet {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void LoCaLog(Component debug){
        Minecraft minecraft=Minecraft.getInstance();
        if (minecraft.player != null) {
            if (minecraft.player.getServer()!=null) {
                Player ServerPlayer = minecraft.player.getServer().overworld().getPlayerByUUID(minecraft.player.getUUID());
                if (ServerPlayer!=null){
                    ServerPlayer.sendSystemMessage(debug);
                }

            }
        }
    }

    public static void log(String log,int Type){
       if (Type==0){
           LOGGER.debug(log);
       } else if (Type==1) {
           LOGGER.error(log);
       }
    }

    public static String ModKey(String key){
        return MutantEscape.MODID+"_" +key.toLowerCase().replace(" ","_");
    }






}
