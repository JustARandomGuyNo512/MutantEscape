package mutantescape.level.register.event;

import mutantescape.tools.ModSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;

import java.util.stream.Stream;

public class BiomeGeneration  {
    public BiomeGeneration(MinecraftServer server) {
        Stream<RegistryAccess.RegistryEntry<?>> configuredFeatureRegistry = server.registryAccess().registries();
        configuredFeatureRegistry.forEach(
                registryEntry -> {
                    ModSet.Debug( registryEntry.key().registry().getPath());
                }
        );
    }


}
