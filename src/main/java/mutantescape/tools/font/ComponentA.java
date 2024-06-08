package mutantescape.tools.font;

import com.mojang.brigadier.Message;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.LiteralContents;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ComponentA extends FormattedText, Message,Component {


    default <T> Optional<T> visit(FormattedText.@NotNull ContentConsumer<T> pAcceptor) {
        Optional<T> optional = this.getContents().visit(pAcceptor);
        if (optional.isPresent()) {
            return optional;
        } else {
            for(Component component : this.getSiblings()) {
                Optional<T> optional1 = component.visit(pAcceptor);
                if (optional1.isPresent()) {
                    return optional1;
                }
            }

            return Optional.empty();
        }
    }




    List<Component> getSiblings();
    Style getStyle();

    default String getStringA() {
        StringBuilder stringbuilder = new StringBuilder();
        this.visit((Acceptor) -> {
            stringbuilder.append(Acceptor);
            return Optional.empty();
        });
        return stringbuilder.toString();
    }


    ComponentContents getContents();
    default <T> Optional<T> visit(StyledContentConsumer<T> pAcceptor, Style pStyle) {
        Style style = this.getStyle().applyTo(pStyle);
        Optional<T> optional = this.getContents().visit(pAcceptor, style);
        if (optional.isPresent()) {
            return optional;
        } else {
            for(Component component : this.getSiblings()) {
                Optional<T> optional1 = component.visit(pAcceptor, style);
                if (optional1.isPresent()) {
                    return optional1;
                }
            }

            return Optional.empty();
        }
    }
    static MutableComponetA literal(String pText) {
        return MutableComponetA.create(new LiteralContents(pText)
        );
    }


}
