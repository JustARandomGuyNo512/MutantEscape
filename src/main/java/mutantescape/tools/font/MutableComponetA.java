package mutantescape.tools.font;

import com.google.common.collect.Lists;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class MutableComponetA implements ComponentA {
    private final ComponentContents contents;
    private final List<ComponentA> siblings;
    private Style style;
    private Language decomposedWith;
    private FormattedCharSequence visualOrderText = FormattedCharSequence.EMPTY;
    public MutableComponetA(ComponentContents pContents, List<ComponentA> pSiblings, Style pStyle) {
        this.contents = pContents;
        this.siblings = pSiblings;
        this.style = pStyle;

    }




    public ComponentContents getContents() {
        return this.contents;
    }

    /**
     * Gets the sibling components of this one.
     */




    public List<Component> getSiblings() {
        List<Component> test2=new ArrayList<>();
        this.siblings.forEach(
                componentA -> {
                    Component component1=Component.literal(componentA.getStringA()).setStyle(componentA.getStyle());
                    test2.add(component1);
                }
        );
        return test2;
    }

    public FormattedCharSequence getVisualOrderText() {
        Language language = Language.getInstance();
        if (this.decomposedWith != language) {
            this.visualOrderText = language.getVisualOrder(this);
            this.decomposedWith = language;
        }

        return this.visualOrderText;
    }

    @Override
    public Style getStyle() {
        return this.style;
    }

    /**
     * Sets the style for this component and returns the component itself.
     */




    public MutableComponetA append(ComponentA pSibling) {
        this.siblings.add(pSibling);
        return this;
    }

    public static MutableComponetA create(ComponentContents pContents) {
        return new MutableComponetA(pContents, Lists.newArrayList(), Style.EMPTY);
    }


    public MutableComponetA setStyle(Style pStyle) {
        this.style = pStyle;
        return this;
    }
}
