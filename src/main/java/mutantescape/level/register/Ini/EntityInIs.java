package mutantescape.level.register.Ini;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class EntityInIs {
  public static class EIni{
      public EIni(RegistryObject<EntityType<?>> entity, int level) {
           this.entity = entity;
           this.level = level;
       }

         RegistryObject<EntityType<?>> entity;
         int level;



    }


   public static HashMap<String,EIni> ENTITYS_INI=new HashMap<>();



}
