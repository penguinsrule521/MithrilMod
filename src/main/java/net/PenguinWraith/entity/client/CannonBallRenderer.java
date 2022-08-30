package net.PenguinWraith.entity.client;

import net.PenguinWraith.MithrilMain;
import net.PenguinWraith.entity.custom.CannonBallEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class CannonBallRenderer extends GeoProjectilesRenderer<CannonBallEntity> {

    public CannonBallRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CannonBallModel());
    }

    @Override
    public Identifier getTextureResource(CannonBallEntity instance) {
        return new Identifier(MithrilMain.MOD_ID, "textures/entity/cannon_ball/cannon_ball.png");
    }
}
