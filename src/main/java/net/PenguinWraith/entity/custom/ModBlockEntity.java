package net.PenguinWraith.entity.custom;

import net.PenguinWraith.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModBlockEntity extends FallingBlockEntity {

    private BlockState block;

    public ModBlockEntity(EntityType<? extends ModBlockEntity> entityType, World world) {
        super(entityType, world);
        this.block = Blocks.SAND.getDefaultState();
        this.dropItem = true;
    }

    private ModBlockEntity(World world, double x, double y, double z, BlockState block) {
        this(ModEntities.BLOCK_ENTITY, world);
        this.block = block;
        this.intersectionChecked = true;
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.setFallingBlockPos(this.getBlockPos());
    }

    public static ModBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
        ModBlockEntity fallingBlockEntity = new ModBlockEntity(world, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? (BlockState)state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), 3);
        world.spawnEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
