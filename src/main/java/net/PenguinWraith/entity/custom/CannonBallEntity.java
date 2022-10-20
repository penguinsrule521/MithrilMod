package net.PenguinWraith.entity.custom;

import net.PenguinWraith.entity.ModEntities;
import net.PenguinWraith.items.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CannonBallEntity extends PersistentProjectileEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    private int explosionPower = 2;

    public CannonBallEntity(EntityType<? extends CannonBallEntity> entityType, World world) {
        super(entityType, world);
    }

    public CannonBallEntity(World world, LivingEntity owner) {
        super(ModEntities.CANNON_BALL, owner, world);
    }

    public CannonBallEntity(World world, double x, double y, double z) {
        super(ModEntities.CANNON_BALL, x, y, z, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.CANNON_BALL);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
        if (!this.world.isClient) {
            if (!state.isAir()) {
                this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), (float) this.explosionPower, Explosion.DestructionType.DESTROY);
                this.discard();
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
            this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), (float) this.explosionPower, Explosion.DestructionType.DESTROY);
            this.discard();
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cannon_ball.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
