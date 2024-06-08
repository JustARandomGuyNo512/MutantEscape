package mutantescape.tools;

import mutantescape.tools.font.ComponentA;
import mutantescape.tools.font.MutableComponetA;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {
    static MutableComponetA component;

   static class SY{
        public SY(String t1, String t2, String t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }

        String t1;
        String t2;
        String t3;
    }

    public static ArrayList<SY> SetInput(String string){
        String regex = "<#Color:([0-9a-fA-F]{6})>(.*?)</Color>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        ArrayList<SY> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(new SY(matcher.group(0), "#"+matcher.group(1), matcher.group(2))); // 获取完整匹配到的字符串
        }
        return matches;
    }

   public static Component GetText(Entity entity, String string){
       component= ComponentA.literal("");
       ArrayList<SY> Sytext = SetInput(string);
       Sytext.forEach(
               sy -> {
                       String Text =sy.t3;
                       if (Minecraft.getInstance().level != null && entity instanceof Player player) {
                           long worldTime = player.level().getDayTime();
                           int day = (int) (worldTime / 24000L);
                           long timeOfDay = worldTime % 24000L;
                           int hour = (int) ((timeOfDay + 6000L) / 1000L) % 24;
                           int minute = (int) ((timeOfDay % 1000L) / 16.666D);
                           Text=Text.replace("<player>", player.getDisplayName().getString());
                           Text=Text.replace("<level>",Minecraft.getInstance().level.dimension().location().getPath());
                           Text=Text.replace("<player-x>",String.valueOf(player.getBlockX()));
                           Text=Text.replace("<player-y>",String.valueOf(player.getBlockY()));
                           Text=Text.replace("<player-z>",String.valueOf(player.getBlockZ()));
                           Text=Text.replace("<time-day>",String.valueOf(day));
                           Text=Text.replace("<time-hour>",String.valueOf(hour));
                           Text=Text.replace("<time-minute>",String.valueOf(minute));
                           component.append( ComponentA.literal(Text).setStyle(Style.EMPTY.withColor(TextColor.parseColor(sy.t2))));
                       }

               }
       );
       return  component;

   }









}
