package net.PenguinWraith.entity.client;

import net.PenguinWraith.entity.custom.ModBlockEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BlockEntityRenderer extends EntityRenderer<ModBlockEntity> {

    protected BlockEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ModBlockEntity entity) {
        return null;
    }
}
