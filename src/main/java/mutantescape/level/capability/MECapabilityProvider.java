package mutantescape.level.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MECapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<MECapaAttribute> PLAYER_ATTRIBUTE= CapabilityManager.get(new CapabilityToken<MECapaAttribute>() {
    }); private MECapaAttribute H2ATTRIBUTE=null;

    private final LazyOptional<MECapaAttribute> optional=LazyOptional.of(this::createAttrube);

    private MECapaAttribute createAttrube() {
        if (this.H2ATTRIBUTE==null){
            this.H2ATTRIBUTE=new MECapaAttribute();
        }
        return this.H2ATTRIBUTE;

    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap==PLAYER_ATTRIBUTE){
            return optional.cast();
        }
        return  LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag IAttrube=new CompoundTag();
        createAttrube().savaNBtData(IAttrube);
        return IAttrube;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createAttrube().loadNbtData(nbt);
    }

}
