package net.PenguinWraith.entity.custom;

import com.mojang.logging.LogUtils;
import net.PenguinWraith.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;


public class ModFallingBlockEntity extends Entity {

    private static final Logger field_36333 = LogUtils.getLogger();
    private BlockState block;
    public int timeFalling;
    public boolean dropItem;
    private boolean hurtEntities;
    private int fallHurtMax;
    private float fallHurtAmount;
    @Nullable
    public NbtCompound blockEntityData;
    protected static final TrackedData<BlockPos> BLOCK_POS;

    public ModFallingBlockEntity(EntityType<? extends ModFallingBlockEntity> entityType, World world) {
        super(entityType, world);
        this.block = Blocks.SAND.getDefaultState();
        this.dropItem = true;
        this.fallHurtMax = 40;
    }

    private ModFallingBlockEntity(World world, double x, double y, double z, BlockState block) {
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

    public static ModFallingBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
        ModFallingBlockEntity fallingBlockEntity = new ModFallingBlockEntity(world, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? (BlockState)state.with(Properties.WATERLOGGED, false) : state);
        world.setBlockState(pos, state.getFluidState().getBlockState(), 3);
        world.spawnEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        if (this.block.isOpaque())
            return true;
        else
            return false;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    public void setFallingBlockPos(BlockPos pos) {
        this.dataTracker.set(BLOCK_POS, pos);
    }

    public BlockPos getFallingBlockPos() {
        return (BlockPos)this.dataTracker.get(BLOCK_POS);
    }

    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(BLOCK_POS, BlockPos.ORIGIN);
    }

    public boolean canHit() {
        return !this.isRemoved();
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put("BlockState", NbtHelper.fromBlockState(this.block));
        nbt.putInt("Time", this.timeFalling);
        nbt.putBoolean("DropItem", this.dropItem);
        nbt.putBoolean("HurtEntities", this.hurtEntities);
        nbt.putFloat("FallHurtAmount", this.fallHurtAmount);
        nbt.putInt("FallHurtMax", this.fallHurtMax);
        if (this.blockEntityData != null) {
            nbt.put("TileEntityData", this.blockEntityData);
        }

    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.block = NbtHelper.toBlockState(nbt.getCompound("BlockState"));
        this.timeFalling = nbt.getInt("Time");
        if (nbt.contains("HurtEntities", 99)) {
            this.hurtEntities = nbt.getBoolean("HurtEntities");
            this.fallHurtAmount = nbt.getFloat("FallHurtAmount");
            this.fallHurtMax = nbt.getInt("FallHurtMax");
        } else if (this.block.isIn(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }

        if (nbt.contains("DropItem", 99)) {
            this.dropItem = nbt.getBoolean("DropItem");
        }

        if (nbt.contains("TileEntityData", 10)) {
            this.blockEntityData = nbt.getCompound("TileEntityData");
        }

        if (this.block.isAir()) {
            this.block = Blocks.SAND.getDefaultState();
        }

    }

    public void setHurtEntities(float fallHurtAmount, int fallHurtMax) {
        this.hurtEntities = true;
        this.fallHurtAmount = fallHurtAmount;
        this.fallHurtMax = fallHurtMax;
    }

    public boolean doesRenderOnFire() {
        return false;
    }

    public void populateCrashReport(CrashReportSection section) {
        super.populateCrashReport(section);
        section.add("Immitating BlockState", this.block.toString());
    }

    public BlockState getBlockState() {
        return this.block;
    }

    public boolean entityDataRequiresOperator() {
        return true;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockState()));
    }

    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        this.block = Block.getStateFromRawId(packet.getEntityData());
        this.intersectionChecked = true;
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        this.setPosition(d, e, f);
        this.setFallingBlockPos(this.getBlockPos());
    }

    static {
        BLOCK_POS = DataTracker.registerData(ModFallingBlockEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    }
}
