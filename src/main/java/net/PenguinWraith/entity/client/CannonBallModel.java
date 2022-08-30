package net.PenguinWraith.entity.client;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.entity.custom.CannonBallEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CannonBallModel extends AnimatedGeoModel<CannonBallEntity> {

    @Override
    public Identifier getModelResource(CannonBallEntity object) {
        return new Identifier(MithrilMain.MOD_ID, "geo/cannon_ball.geo.json");
    }

    @Override
    public Identifier getTextureResource(CannonBallEntity object) {
        return new Identifier(MithrilMain.MOD_ID, "textures/entity/cannon_ball/cannon_ball.png");
    }

    @Override
    public Identifier getAnimationResource(CannonBallEntity animatable) {
        return new Identifier(MithrilMain.MOD_ID, "animations/cannon_ball.animation.json");
    }
}
