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
    public static Capability<MECapabilityAttributes> PLAYER_ATTRIBUTE= CapabilityManager.get(new CapabilityToken<>() {});

    private MECapabilityAttributes H2ATTRIBUTE=null;

    private final LazyOptional<MECapabilityAttributes> optional=LazyOptional.of(this::createAttribute);

    @NotNull
    private MECapabilityAttributes createAttribute() {
        if (this.H2ATTRIBUTE==null){
            this.H2ATTRIBUTE=new MECapabilityAttributes();
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
        CompoundTag IAttribute=new CompoundTag();
        createAttribute().saveNbtData(IAttribute);
        return IAttribute;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createAttribute().loadNbtData(nbt);
    }

}
