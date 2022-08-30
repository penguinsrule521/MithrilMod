package net.PenguinWraith.world.feature;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.blocks.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreFeatureConfig.Target> OVERWORLD_MITHRIL_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.MITHRIL_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_MITHRIL_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> MITHRIL_ORE =
            ConfiguredFeatures.register("mithril_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_MITHRIL_ORES, 3));



    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + MithrilMain.MOD_ID);
    }
}
