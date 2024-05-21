package mutantescape;

import mutantescape.level.capability.MEAttribute;
import mutantescape.level.capability.MECapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class Clients {

    //将服务的广播来的数据更新到本地客户端对应实体
    public static void updateClientPlayerAttributes(int entityID, List<MEAttribute> attributeList) {
        ClientLevel clientLevel = Minecraft.getInstance().level;
        if (clientLevel != null) {
            Entity entity = clientLevel.getEntity(entityID);
            if (entity instanceof Player player) {
                player.getCapability(MECapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    for (MEAttribute attribute : attributeList) {
                        MEAttribute target = capability.getProfession().get(attribute.getID());
                        if (target != null) {
                            target.copyFrom(attribute);
                            target.setNeedSync(false);
                        }
                    }
                }));
            }
        }
    }


}
