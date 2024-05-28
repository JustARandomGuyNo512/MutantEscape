package mutantescape.tools;

import com.mojang.logging.LogUtils;
import mutantescape.MutantEscape;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;

public class ModSet {
    public static ServerPlayer ModServerPlayer;
    public static ServerLevel ModServerLevel;
    static {
       Minecraft MC= Minecraft.getInstance();
            if (MC.level!=null && MC.level.getServer() != null && MC.player != null) {
                ModServerPlayer = MC.level.getServer().getPlayerList().getPlayer(MC.player.getUUID());
                ModServerLevel=MC.level.getServer().overworld();


            }
    }
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void LocatePlayerSendLog(Component debug){
                if (ModServerPlayer!=null){
                    ModServerPlayer.sendSystemMessage(debug);
                }
    }





    public static void Debug(String log,int Type){
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
