package net.PenguinWraith.items;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.items.custom.DaggerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item WOODEN_DAGGER = registerItem("wooden_dagger",
            new DaggerItem(ToolMaterials.WOOD, 0, -2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item STONE_DAGGER = registerItem("stone_dagger",
            new DaggerItem(ToolMaterials.STONE, 0, -2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item IRON_DAGGER = registerItem("iron_dagger",
            new DaggerItem(ToolMaterials.IRON, 0, -2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item DIAMOND_DAGGER = registerItem("diamond_dagger",
            new DaggerItem(ToolMaterials.DIAMOND, 0, -2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item NETHERITE_DAGGER = registerItem("netherite_dagger",
            new DaggerItem(ToolMaterials.NETHERITE, 0, -2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item MITHRIL_INGOT = registerItem("mithril_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_NUGGET = registerItem("mithril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item RAW_MITHRIL = registerItem("raw_mithril",
            new Item(new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_SWORD = registerItem("mithril_sword",
            new SwordItem(ModToolMaterials.MITHRIL, 3, -2f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe",
            new ModPickaxeItem(ModToolMaterials.MITHRIL, 1, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_AXE = registerItem("mithril_axe",
            new ModAxeItem(ModToolMaterials.MITHRIL, 5, -2.6f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel",
            new ShovelItem(ModToolMaterials.MITHRIL, 1.5f, -2.6f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_HOE = registerItem("mithril_hoe",
            new ModHoeItem(ModToolMaterials.MITHRIL, -5, .4f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item MITHRIL_DAGGER = registerItem("mithril_dagger",
            new DaggerItem(ModToolMaterials.MITHRIL, 0, -1.6f,
                    new FabricItemSettings().group(ModItemGroup.MITHRIL)));

    public static final Item CANNON_BALL = registerItem("cannon_ball",
            new Item(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(16)));

    public static final Item CANNON_BARREL = registerItem("cannon_barrel",
            new Item(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MithrilMain.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MithrilMain.LOGGER.info("Registering mod items for " + MithrilMain.MOD_ID);
    }
}
