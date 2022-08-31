package net.PenguinWraith.blocks.custom;

import net.PenguinWraith.blocks.ModBlocks;
import net.PenguinWraith.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CannonCartBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CannonCartBlock(Settings settings) {
        super(settings);
    }

    public static final VoxelShape SHAPE_N = Stream.of(
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
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
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
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
