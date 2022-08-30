package net.PenguinWraith.blocks.custom;

import net.PenguinWraith.blocks.ModBlocks;
import net.PenguinWraith.entity.ModEntities;
import net.PenguinWraith.entity.custom.CannonBallEntity;
import net.PenguinWraith.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.EntityList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CannonBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static BooleanProperty LOADED = Properties.TRIGGERED;

    public CannonBlock(Settings settings) {
        super(settings);
    }

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(11, 7, -15, 12, 13, 14),
            Block.createCuboidShape(4, 7, -15, 5, 13, 14),
            Block.createCuboidShape(5, 6, -15, 11, 7, 14),
            Block.createCuboidShape(5, 13, -15, 11, 14, 14),
            Block.createCuboidShape(5, 7, 14, 11, 13, 15),
            Block.createCuboidShape(6, 8, 15, 10, 12, 16),
            Block.createCuboidShape(6, 14, 8, 10, 15, 12),
            Block.createCuboidShape(3, 0, 0, 13, 1, 16),
            Block.createCuboidShape(3, 1, 0, 4, 10, 6),
            Block.createCuboidShape(12, 1, 0, 13, 10, 6),
            Block.createCuboidShape(3, 1, 6, 4, 9, 8),
            Block.createCuboidShape(12, 1, 6, 13, 9, 8),
            Block.createCuboidShape(3, 1, 8, 4, 8, 10),
            Block.createCuboidShape(12, 1, 8, 13, 8, 10),
            Block.createCuboidShape(3, 1, 10, 4, 7, 12),
            Block.createCuboidShape(12, 1, 10, 13, 7, 12),
            Block.createCuboidShape(3, 1, 12, 4, 6, 14),
            Block.createCuboidShape(12, 1, 12, 13, 6, 14),
            Block.createCuboidShape(3, 1, 14, 4, 5, 16),
            Block.createCuboidShape(12, 1, 14, 13, 5, 16),
            Block.createCuboidShape(4, 1, 1, 12, 6, 2),
            Block.createCuboidShape(4, 6, 1, 5, 7, 2),
            Block.createCuboidShape(11, 6, 1, 12, 7, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2, 7, 11, 31, 13, 12),
            Block.createCuboidShape(2, 7, 4, 31, 13, 5),
            Block.createCuboidShape(2, 6, 5, 31, 7, 11),
            Block.createCuboidShape(2, 13, 5, 31, 14, 11),
            Block.createCuboidShape(1, 7, 5, 2, 13, 11),
            Block.createCuboidShape(0, 8, 6, 1, 12, 10),
            Block.createCuboidShape(4, 14, 6, 8, 15, 10),
            Block.createCuboidShape(0, 0, 3, 16, 1, 13),
            Block.createCuboidShape(10, 1, 3, 16, 10, 4),
            Block.createCuboidShape(10, 1, 12, 16, 10, 13),
            Block.createCuboidShape(8, 1, 3, 10, 9, 4),
            Block.createCuboidShape(8, 1, 12, 10, 9, 13),
            Block.createCuboidShape(6, 1, 3, 8, 8, 4),
            Block.createCuboidShape(6, 1, 12, 8, 8, 13),
            Block.createCuboidShape(4, 1, 3, 6, 7, 4),
            Block.createCuboidShape(4, 1, 12, 6, 7, 13),
            Block.createCuboidShape(2, 1, 3, 4, 6, 4),
            Block.createCuboidShape(2, 1, 12, 4, 6, 13),
            Block.createCuboidShape(0, 1, 3, 2, 5, 4),
            Block.createCuboidShape(0, 1, 12, 2, 5, 13),
            Block.createCuboidShape(14, 1, 4, 15, 6, 12),
            Block.createCuboidShape(14, 6, 4, 15, 7, 5),
            Block.createCuboidShape(14, 6, 11, 15, 7, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(4, 7, 2, 5, 13, 31),
            Block.createCuboidShape(11, 7, 2, 12, 13, 31),
            Block.createCuboidShape(5, 6, 2, 11, 7, 31),
            Block.createCuboidShape(5, 13, 2, 11, 14, 31),
            Block.createCuboidShape(5, 7, 1, 11, 13, 2),
            Block.createCuboidShape(6, 8, 0, 10, 12, 1),
            Block.createCuboidShape(6, 14, 4, 10, 15, 8),
            Block.createCuboidShape(3, 0, 0, 13, 1, 16),
            Block.createCuboidShape(12, 1, 10, 13, 10, 16),
            Block.createCuboidShape(3, 1, 10, 4, 10, 16),
            Block.createCuboidShape(12, 1, 8, 13, 9, 10),
            Block.createCuboidShape(3, 1, 8, 4, 9, 10),
            Block.createCuboidShape(12, 1, 6, 13, 8, 8),
            Block.createCuboidShape(3, 1, 6, 4, 8, 8),
            Block.createCuboidShape(12, 1, 4, 13, 7, 6),
            Block.createCuboidShape(3, 1, 4, 4, 7, 6),
            Block.createCuboidShape(12, 1, 2, 13, 6, 4),
            Block.createCuboidShape(3, 1, 2, 4, 6, 4),
            Block.createCuboidShape(12, 1, 0, 13, 5, 2),
            Block.createCuboidShape(3, 1, 0, 4, 5, 2),
            Block.createCuboidShape(4, 1, 14, 12, 6, 15),
            Block.createCuboidShape(11, 6, 14, 12, 7, 15),
            Block.createCuboidShape(4, 6, 14, 5, 7, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(-15, 7, 4, 14, 13, 5),
            Block.createCuboidShape(-15, 7, 11, 14, 13, 12),
            Block.createCuboidShape(-15, 6, 5, 14, 7, 11),
            Block.createCuboidShape(-15, 13, 5, 14, 14, 11),
            Block.createCuboidShape(14, 7, 5, 15, 13, 11),
            Block.createCuboidShape(15, 8, 6, 16, 12, 10),
            Block.createCuboidShape(8, 14, 6, 12, 15, 10),
            Block.createCuboidShape(0, 0, 3, 16, 1, 13),
            Block.createCuboidShape(0, 1, 12, 6, 10, 13),
            Block.createCuboidShape(0, 1, 3, 6, 10, 4),
            Block.createCuboidShape(6, 1, 12, 8, 9, 13),
            Block.createCuboidShape(6, 1, 3, 8, 9, 4),
            Block.createCuboidShape(8, 1, 12, 10, 8, 13),
            Block.createCuboidShape(8, 1, 3, 10, 8, 4),
            Block.createCuboidShape(10, 1, 12, 12, 7, 13),
            Block.createCuboidShape(10, 1, 3, 12, 7, 4),
            Block.createCuboidShape(12, 1, 12, 14, 6, 13),
            Block.createCuboidShape(12, 1, 3, 14, 6, 4),
            Block.createCuboidShape(14, 1, 12, 16, 5, 13),
            Block.createCuboidShape(14, 1, 3, 16, 5, 4),
            Block.createCuboidShape(1, 1, 4, 2, 6, 12),
            Block.createCuboidShape(1, 6, 11, 2, 7, 12),
            Block.createCuboidShape(1, 6, 4, 2, 7, 5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(LOADED, false);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        if (state.get(LOADED)) {
            List<ItemStack> list = new ArrayList<>();
            list.add(new ItemStack(ModItems.CANNON_BALL));
            list.add(new ItemStack(ModBlocks.CANNON_CART));
            list.add(new ItemStack(ModItems.CANNON_BARREL));
            return list;
        } else {
            List<ItemStack> list = new ArrayList<>();
            list.add(new ItemStack(ModBlocks.CANNON_CART));
            list.add(new ItemStack(ModItems.CANNON_BARREL));
            return list;
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LOADED);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public static void fire(PlayerEntity player, BlockPos pos, BlockState state, DirectionProperty dir) {

        player.world.playSound(null, pos, SoundEvents.ENTITY_CREEPER_PRIMED,
                SoundCategory.HOSTILE, 1, 1);

        CannonBallEntity cannonBall;
        float yaw;
        if (state.get(dir).equals(Direction.NORTH)) {
            yaw = -180;
            cannonBall = new CannonBallEntity(ModEntities.CANNON_BALL, player.getWorld(), pos.getX() + 0.5D, pos.getY() + 0.4375D, pos.getZ() - 1.5D);
        } else if (state.get(dir).equals(Direction.EAST)) {
            yaw = -90;
            cannonBall = new CannonBallEntity(ModEntities.CANNON_BALL, player.getWorld(), pos.getX() + 2.5D, pos.getY() + 0.4375D, pos.getZ() + 0.5D);
        } else if (state.get(dir).equals(Direction.SOUTH)) {
            yaw = 0;
            cannonBall = new CannonBallEntity(ModEntities.CANNON_BALL, player.getWorld(), pos.getX() + 0.5D, pos.getY() + 0.4375D, pos.getZ() + 2.5D);
        } else {
            yaw = 90;
            cannonBall = new CannonBallEntity(ModEntities.CANNON_BALL, player.getWorld(), pos.getX() - 1.5D, pos.getY() + 0.4375D, pos.getZ() + 0.5D);
        }

        cannonBall.setVelocity(player, 0, yaw, 0, 5F, 1.0F);
        player.getWorld().spawnEntity(cannonBall);
        player.world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.HOSTILE, 1, 1);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {

            if (player.getStackInHand(hand).getItem().equals(Items.FLINT_AND_STEEL) && state.get(LOADED)) {

                if (hand.equals(Hand.MAIN_HAND)) {
                    player.getStackInHand(hand).damage(1, player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });
                }
                if (hand.equals(Hand.OFF_HAND)) {
                    player.getStackInHand(hand).damage(1, player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND);
                    });
                }

                if (world.getBlockState(pos.add(hit.getSide().getVector())).getBlock().equals(Blocks.FIRE))
                    world.setBlockState(pos.add(hit.getSide().getVector()), Blocks.AIR.getDefaultState());

                fire(player, pos, state, FACING);

                return ActionResult.SUCCESS;
            } else if (player.getStackInHand(hand).getItem().equals(ModItems.CANNON_BALL) && !state.get(LOADED)) {
                player.sendMessage(Text.of("LOADED"));
                world.setBlockState(pos, state.cycle(LOADED));
                player.getStackInHand(hand).setCount(player.getStackInHand(hand).getCount() - 1);
                player.world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                        SoundCategory.HOSTILE, 1, 1);

                return ActionResult.SUCCESS;
            } else if (state.get(LOADED)) {
                player.sendMessage(Text.of("UNLOADED"));
                world.setBlockState(pos, state.cycle(LOADED));
                player.getInventory().insertStack(new ItemStack(ModItems.CANNON_BALL, 1));
                player.world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                        SoundCategory.HOSTILE, 1, 1);

                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
        } else {
            return ActionResult.FAIL;
        }
    }
}
