package mutantescape.level.network.UpdateDataCient;

import mutantescape.level.capability.Attribute;
import mutantescape.level.capability.CapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class Clients {

    //将服务的广播来的数据更新到本地客户端对应实体
    public static void updateClientPlayerAttributes(int entityID, List<Attribute> attributeList) {
        ClientLevel clientLevel = Minecraft.getInstance().level;
        if (clientLevel != null) {
            Entity entity = clientLevel.getEntity(entityID);
            if (entity instanceof Player player) {
                player.getCapability(CapabilityProvider.PLAYER_ATTRIBUTE).ifPresent((capability -> {
                    for (Attribute attribute : attributeList) {
                        Attribute target = capability.getProfession().get(attribute.getID());
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
