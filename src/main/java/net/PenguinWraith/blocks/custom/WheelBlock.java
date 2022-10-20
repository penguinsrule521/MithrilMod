package net.PenguinWraith.blocks.custom;

import net.PenguinWraith.entity.custom.ModFallingBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WheelBlock extends WallMountedBlock {

    private ArrayList<Position> ship = new ArrayList<>();
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public WheelBlock(Settings settings) {
        super(settings);
    }

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(6, 2, 13, 10, 3, 14),
            Block.createCuboidShape(4, 3, 13, 12, 4, 14),
            Block.createCuboidShape(3, 4, 13, 4, 12, 14),
            Block.createCuboidShape(2, 6, 13, 3, 10, 14),
            Block.createCuboidShape(4, 12, 13, 12, 13, 14),
            Block.createCuboidShape(6, 13, 13, 10, 14, 14),
            Block.createCuboidShape(12, 4, 13, 13, 12, 14),
            Block.createCuboidShape(13, 6, 13, 14, 10, 14),
            Block.createCuboidShape(7, 0, 13, 9, 2, 14),
            Block.createCuboidShape(7, 14, 13, 9, 16, 14),
            Block.createCuboidShape(14, 7, 13, 16, 9, 14),
            Block.createCuboidShape(0, 7, 13, 2, 9, 14),
            Block.createCuboidShape(2, 2, 13, 4, 4, 14),
            Block.createCuboidShape(2, 1, 13, 3, 2, 14),
            Block.createCuboidShape(1, 2, 13, 2, 3, 14),
            Block.createCuboidShape(12, 2, 13, 14, 4, 14),
            Block.createCuboidShape(13, 1, 13, 14, 2, 14),
            Block.createCuboidShape(14, 2, 13, 15, 3, 14),
            Block.createCuboidShape(12, 12, 13, 14, 14, 14),
            Block.createCuboidShape(13, 14, 13, 14, 15, 14),
            Block.createCuboidShape(14, 13, 13, 15, 14, 14),
            Block.createCuboidShape(2, 12, 13, 4, 14, 14),
            Block.createCuboidShape(2, 14, 13, 3, 15, 14),
            Block.createCuboidShape(1, 13, 13, 2, 14, 14),
            Block.createCuboidShape(4, 7, 13, 12, 9, 14),
            Block.createCuboidShape(7, 9, 13, 9, 12, 14),
            Block.createCuboidShape(7, 4, 13, 9, 7, 14),
            Block.createCuboidShape(4, 4, 13, 5, 5, 14),
            Block.createCuboidShape(11, 4, 13, 12, 5, 14),
            Block.createCuboidShape(11, 11, 13, 12, 12, 14),
            Block.createCuboidShape(4, 11, 13, 5, 12, 14),
            Block.createCuboidShape(5, 5, 13, 7, 7, 14),
            Block.createCuboidShape(9, 5, 13, 11, 7, 14),
            Block.createCuboidShape(9, 9, 13, 11, 11, 14),
            Block.createCuboidShape(5, 9, 13, 7, 11, 14),
            Block.createCuboidShape(4, 10, 13, 5, 11, 14),
            Block.createCuboidShape(5, 11, 13, 6, 12, 14),
            Block.createCuboidShape(10, 11, 13, 11, 12, 14),
            Block.createCuboidShape(11, 10, 13, 12, 11, 14),
            Block.createCuboidShape(11, 5, 13, 12, 6, 14),
            Block.createCuboidShape(10, 4, 13, 11, 5, 14),
            Block.createCuboidShape(4, 5, 13, 5, 6, 14),
            Block.createCuboidShape(5, 4, 13, 6, 5, 14),
            Block.createCuboidShape(7, 7, 14, 9, 9, 15),
            Block.createCuboidShape(6, 6, 15, 10, 10, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2, 2, 6, 3, 3, 10),
            Block.createCuboidShape(2, 3, 4, 3, 4, 12),
            Block.createCuboidShape(2, 4, 3, 3, 12, 4),
            Block.createCuboidShape(2, 6, 2, 3, 10, 3),
            Block.createCuboidShape(2, 12, 4, 3, 13, 12),
            Block.createCuboidShape(2, 13, 6, 3, 14, 10),
            Block.createCuboidShape(2, 4, 12, 3, 12, 13),
            Block.createCuboidShape(2, 6, 13, 3, 10, 14),
            Block.createCuboidShape(2, 0, 7, 3, 2, 9),
            Block.createCuboidShape(2, 14, 7, 3, 16, 9),
            Block.createCuboidShape(2, 7, 14, 3, 9, 16),
            Block.createCuboidShape(2, 7, 0, 3, 9, 2),
            Block.createCuboidShape(2, 2, 2, 3, 4, 4),
            Block.createCuboidShape(2, 1, 2, 3, 2, 3),
            Block.createCuboidShape(2, 2, 1, 3, 3, 2),
            Block.createCuboidShape(2, 2, 12, 3, 4, 14),
            Block.createCuboidShape(2, 1, 13, 3, 2, 14),
            Block.createCuboidShape(2, 2, 14, 3, 3, 15),
            Block.createCuboidShape(2, 12, 12, 3, 14, 14),
            Block.createCuboidShape(2, 14, 13, 3, 15, 14),
            Block.createCuboidShape(2, 13, 14, 3, 14, 15),
            Block.createCuboidShape(2, 12, 2, 3, 14, 4),
            Block.createCuboidShape(2, 14, 2, 3, 15, 3),
            Block.createCuboidShape(2, 13, 1, 3, 14, 2),
            Block.createCuboidShape(2, 7, 4, 3, 9, 12),
            Block.createCuboidShape(2, 9, 7, 3, 12, 9),
            Block.createCuboidShape(2, 4, 7, 3, 7, 9),
            Block.createCuboidShape(2, 4, 4, 3, 5, 5),
            Block.createCuboidShape(2, 4, 11, 3, 5, 12),
            Block.createCuboidShape(2, 11, 11, 3, 12, 12),
            Block.createCuboidShape(2, 11, 4, 3, 12, 5),
            Block.createCuboidShape(2, 5, 5, 3, 7, 7),
            Block.createCuboidShape(2, 5, 9, 3, 7, 11),
            Block.createCuboidShape(2, 9, 9, 3, 11, 11),
            Block.createCuboidShape(2, 9, 5, 3, 11, 7),
            Block.createCuboidShape(2, 10, 4, 3, 11, 5),
            Block.createCuboidShape(2, 11, 5, 3, 12, 6),
            Block.createCuboidShape(2, 11, 10, 3, 12, 11),
            Block.createCuboidShape(2, 10, 11, 3, 11, 12),
            Block.createCuboidShape(2, 5, 11, 3, 6, 12),
            Block.createCuboidShape(2, 4, 10, 3, 5, 11),
            Block.createCuboidShape(2, 5, 4, 3, 6, 5),
            Block.createCuboidShape(2, 4, 5, 3, 5, 6),
            Block.createCuboidShape(1, 7, 7, 2, 9, 9),
            Block.createCuboidShape(0, 6, 6, 1, 10, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(6, 2, 2, 10, 3, 3),
            Block.createCuboidShape(4, 3, 2, 12, 4, 3),
            Block.createCuboidShape(12, 4, 2, 13, 12, 3),
            Block.createCuboidShape(13, 6, 2, 14, 10, 3),
            Block.createCuboidShape(4, 12, 2, 12, 13, 3),
            Block.createCuboidShape(6, 13, 2, 10, 14, 3),
            Block.createCuboidShape(3, 4, 2, 4, 12, 3),
            Block.createCuboidShape(2, 6, 2, 3, 10, 3),
            Block.createCuboidShape(7, 0, 2, 9, 2, 3),
            Block.createCuboidShape(7, 14, 2, 9, 16, 3),
            Block.createCuboidShape(0, 7, 2, 2, 9, 3),
            Block.createCuboidShape(14, 7, 2, 16, 9, 3),
            Block.createCuboidShape(12, 2, 2, 14, 4, 3),
            Block.createCuboidShape(13, 1, 2, 14, 2, 3),
            Block.createCuboidShape(14, 2, 2, 15, 3, 3),
            Block.createCuboidShape(2, 2, 2, 4, 4, 3),
            Block.createCuboidShape(2, 1, 2, 3, 2, 3),
            Block.createCuboidShape(1, 2, 2, 2, 3, 3),
            Block.createCuboidShape(2, 12, 2, 4, 14, 3),
            Block.createCuboidShape(2, 14, 2, 3, 15, 3),
            Block.createCuboidShape(1, 13, 2, 2, 14, 3),
            Block.createCuboidShape(12, 12, 2, 14, 14, 3),
            Block.createCuboidShape(13, 14, 2, 14, 15, 3),
            Block.createCuboidShape(14, 13, 2, 15, 14, 3),
            Block.createCuboidShape(4, 7, 2, 12, 9, 3),
            Block.createCuboidShape(7, 9, 2, 9, 12, 3),
            Block.createCuboidShape(7, 4, 2, 9, 7, 3),
            Block.createCuboidShape(11, 4, 2, 12, 5, 3),
            Block.createCuboidShape(4, 4, 2, 5, 5, 3),
            Block.createCuboidShape(4, 11, 2, 5, 12, 3),
            Block.createCuboidShape(11, 11, 2, 12, 12, 3),
            Block.createCuboidShape(9, 5, 2, 11, 7, 3),
            Block.createCuboidShape(5, 5, 2, 7, 7, 3),
            Block.createCuboidShape(5, 9, 2, 7, 11, 3),
            Block.createCuboidShape(9, 9, 2, 11, 11, 3),
            Block.createCuboidShape(11, 10, 2, 12, 11, 3),
            Block.createCuboidShape(10, 11, 2, 11, 12, 3),
            Block.createCuboidShape(5, 11, 2, 6, 12, 3),
            Block.createCuboidShape(4, 10, 2, 5, 11, 3),
            Block.createCuboidShape(4, 5, 2, 5, 6, 3),
            Block.createCuboidShape(5, 4, 2, 6, 5, 3),
            Block.createCuboidShape(11, 5, 2, 12, 6, 3),
            Block.createCuboidShape(10, 4, 2, 11, 5, 3),
            Block.createCuboidShape(7, 7, 1, 9, 9, 2),
            Block.createCuboidShape(6, 6, 0, 10, 10, 1)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(13, 2, 6, 14, 3, 10),
            Block.createCuboidShape(13, 3, 4, 14, 4, 12),
            Block.createCuboidShape(13, 4, 12, 14, 12, 13),
            Block.createCuboidShape(13, 6, 13, 14, 10, 14),
            Block.createCuboidShape(13, 12, 4, 14, 13, 12),
            Block.createCuboidShape(13, 13, 6, 14, 14, 10),
            Block.createCuboidShape(13, 4, 3, 14, 12, 4),
            Block.createCuboidShape(13, 6, 2, 14, 10, 3),
            Block.createCuboidShape(13, 0, 7, 14, 2, 9),
            Block.createCuboidShape(13, 14, 7, 14, 16, 9),
            Block.createCuboidShape(13, 7, 0, 14, 9, 2),
            Block.createCuboidShape(13, 7, 14, 14, 9, 16),
            Block.createCuboidShape(13, 2, 12, 14, 4, 14),
            Block.createCuboidShape(13, 1, 13, 14, 2, 14),
            Block.createCuboidShape(13, 2, 14, 14, 3, 15),
            Block.createCuboidShape(13, 2, 2, 14, 4, 4),
            Block.createCuboidShape(13, 1, 2, 14, 2, 3),
            Block.createCuboidShape(13, 2, 1, 14, 3, 2),
            Block.createCuboidShape(13, 12, 2, 14, 14, 4),
            Block.createCuboidShape(13, 14, 2, 14, 15, 3),
            Block.createCuboidShape(13, 13, 1, 14, 14, 2),
            Block.createCuboidShape(13, 12, 12, 14, 14, 14),
            Block.createCuboidShape(13, 14, 13, 14, 15, 14),
            Block.createCuboidShape(13, 13, 14, 14, 14, 15),
            Block.createCuboidShape(13, 7, 4, 14, 9, 12),
            Block.createCuboidShape(13, 9, 7, 14, 12, 9),
            Block.createCuboidShape(13, 4, 7, 14, 7, 9),
            Block.createCuboidShape(13, 4, 11, 14, 5, 12),
            Block.createCuboidShape(13, 4, 4, 14, 5, 5),
            Block.createCuboidShape(13, 11, 4, 14, 12, 5),
            Block.createCuboidShape(13, 11, 11, 14, 12, 12),
            Block.createCuboidShape(13, 5, 9, 14, 7, 11),
            Block.createCuboidShape(13, 5, 5, 14, 7, 7),
            Block.createCuboidShape(13, 9, 5, 14, 11, 7),
            Block.createCuboidShape(13, 9, 9, 14, 11, 11),
            Block.createCuboidShape(13, 10, 11, 14, 11, 12),
            Block.createCuboidShape(13, 11, 10, 14, 12, 11),
            Block.createCuboidShape(13, 11, 5, 14, 12, 6),
            Block.createCuboidShape(13, 10, 4, 14, 11, 5),
            Block.createCuboidShape(13, 5, 4, 14, 6, 5),
            Block.createCuboidShape(13, 4, 5, 14, 5, 6),
            Block.createCuboidShape(13, 5, 11, 14, 6, 12),
            Block.createCuboidShape(13, 4, 10, 14, 5, 11),
            Block.createCuboidShape(14, 7, 7, 15, 9, 9),
            Block.createCuboidShape(15, 6, 6, 16, 10, 10)
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

        BlockState blockState = world.getBlockState(pos.add(state.get(FACING).getOpposite().getVector()));
        ModFallingBlockEntity.spawnFromBlock(world, pos, state);
        ModFallingBlockEntity.spawnFromBlock(world, pos.add(state.get(FACING).getOpposite().getVector()), blockState);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAt(world, pos, state);
    }

    public static boolean canPlaceAt(WorldView world, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.offset(state.get(FACING).getOpposite());
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, state.get(FACING).getOpposite());
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction[] var2 = ctx.getPlacementDirections();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Direction direction = var2[var4];
            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = (this.getDefaultState().with(FACE, direction == Direction.UP ? WallMountLocation.CEILING : WallMountLocation.FLOOR)).with(FACING, ctx.getPlayerFacing());
            } else {
                blockState = (this.getDefaultState().with(FACE, WallMountLocation.WALL)).with(FACING, direction.getOpposite());
            }

            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }

        return null;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACE, FACING});
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
