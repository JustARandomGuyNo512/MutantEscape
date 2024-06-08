package mutantescape.datagen.lang;


import mutantescape.MutantEscape;
import mutantescape.tools.ModSet;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class zh_cn extends LanguageProvider {
    public zh_cn(PackOutput output, String locale) {
        super(output,  MutantEscape.MODID, locale);
    }
    @Override
    protected void addTranslations() {
        add(MutantEscape.MODID+"."+"log.1","<#Color:337788>欢迎您:</Color> <#Color:00FF7F> <player> </Color> ，<#Color:221760>祝您玩的愉快! </Color>");
        add("ItemGroup."+MutantEscape.MODID+"."+ ModSet.ModKey("item"),"突变逃生");



    }
}
