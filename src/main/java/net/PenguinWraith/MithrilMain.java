package net.PenguinWraith;

import net.PenguinWraith.blocks.ModBlocks;
import net.PenguinWraith.entity.ModEntities;
import net.PenguinWraith.items.ModItems;
import net.PenguinWraith.world.feature.ModConfiguredFeatures;
import net.PenguinWraith.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class MithrilMain implements ModInitializer {
	public static final String MOD_ID = "mithril";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();

		ModWorldGeneration.generateModWorldGen();

		GeckoLib.initialize();
	}
}
