package net.PenguinWraith.entity;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.entity.custom.CannonBallEntity;
import net.PenguinWraith.entity.custom.ModFallingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<CannonBallEntity> CANNON_BALL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MithrilMain.MOD_ID, "cannon_ball"),
            FabricEntityTypeBuilder.<CannonBallEntity>create(SpawnGroup.MISC, CannonBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).build());

    public static final EntityType<ModFallingBlockEntity> BLOCK_ENTITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(MithrilMain.MOD_ID, "block_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ModFallingBlockEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static void registerModEntities() {
        MithrilMain.LOGGER.info("Registering mod entities for " + MithrilMain.MOD_ID);
    }
}
