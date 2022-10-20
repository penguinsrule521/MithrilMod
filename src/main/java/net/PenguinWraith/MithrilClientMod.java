package net.PenguinWraith;

import net.PenguinWraith.blocks.ModBlocks;
import net.PenguinWraith.entity.ModEntities;
import net.PenguinWraith.entity.client.CannonBallRenderer;
import net.PenguinWraith.entity.client.ModFallingBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;

public class MithrilClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CANNON, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.CANNON_BALL, CannonBallRenderer::new);

        EntityRendererRegistry.register(ModEntities.BLOCK_ENTITY, ModFallingBlockEntityRenderer::new);
    }
}
