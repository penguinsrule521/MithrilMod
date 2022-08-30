package net.PenguinWraith.items;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;

public class ModPickaxeItem extends MiningToolItem {

    public ModPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super((float)attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }
}