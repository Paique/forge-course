package com.paiique.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class SpeedyBlock extends Block {
    public SpeedyBlock(Properties properties) {
        super(properties);
    }

    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getDeltaMovement().y);
        if (d < 0.1 && !entity.isSteppingCarefully()) {
            double e = 1.0 + d * 0.2;
            if (entity instanceof Player) {
                entity.sendMessage(new TextComponent("Velocidade: " + e), entity.getUUID());
            }

            entity.setDeltaMovement(entity.getDeltaMovement().multiply(e, 1.0, e));
        }

        super.stepOn(world, pos, state, entity);
    }



    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        level.explode(player, pos.getX(), pos.getY(), pos.getZ(), 2f, Explosion.BlockInteraction.BREAK);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}

