package net.PenguinWraith.blocks;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.blocks.custom.CannonBlock;
import net.PenguinWraith.blocks.custom.CannonCartBlock;
import net.PenguinWraith.blocks.custom.WheelBlock;
import net.PenguinWraith.items.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block MITHRIL_BLOCK = registerBlock("mithril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(10f).requiresTool()), ModItemGroup.MITHRIL);

    public static final Block MITHRIL_ORE = registerBlock("mithril_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(3f).requiresTool()), ModItemGroup.MITHRIL);

    public static final Block DEEPSLATE_MITHRIL_ORE = registerBlock("deepslate_mithril_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), ModItemGroup.MITHRIL);

    public static final Block RAW_MITHRIL_BLOCK = registerBlock("raw_mithril_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(8f).requiresTool()), ModItemGroup.MITHRIL);

    public static final Block CANNON = registerBlockStack("cannon",
            new CannonBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f).nonOpaque()),
            ItemGroup.COMBAT, 1);

    public static final Block CANNON_CART = registerBlockStack("cannon_cart",
            new CannonCartBlock(FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque()),
            ItemGroup.COMBAT, 1);

    public static final Block WHEEL = registerBlockStack("wheel",
            new WheelBlock(FabricBlockSettings.of(Material.STONE).strength(1f).nonOpaque()),
            ItemGroup.TRANSPORTATION, 1);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(MithrilMain.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(MithrilMain.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Block registerBlockStack(String name, Block block, ItemGroup group, int n) {
        registerBlockStackItem(name, block, group, n);
        return Registry.register(Registry.BLOCK, new Identifier(MithrilMain.MOD_ID, name), block);
    }

    private static Item registerBlockStackItem(String name, Block block, ItemGroup group, int n) {
        return Registry.register(Registry.ITEM, new Identifier(MithrilMain.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group).maxCount(n)));
    }

    public static void registerModBlocks() {
        MithrilMain.LOGGER.info("Registering mod blocks for " + MithrilMain.MOD_ID);
    }
}
