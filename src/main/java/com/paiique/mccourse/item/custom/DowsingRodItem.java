package com.paiique.mccourse.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide) {
            BlockPos positionClicked = pContext.getClickedPos();

            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordiantes(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock) {
               player.sendMessage(new TranslatableComponent("item.mccourse.dowsing_rod.no_valuables"), player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        ItemStack itemStack = pContext.getItemInHand();

        int maxDurability = itemStack.getMaxDamage();
        int currentDurability = maxDurability - itemStack.getDamageValue();

        if (currentDurability < 1) {
        pContext.getPlayer().sendMessage(new TextComponent("Whoops!"), pContext.getPlayer().getUUID());
        }


        return super.useOn(pContext);
    }


    private void outputValuableCoordiantes(BlockPos blockPos, Player player, Block blowBelow) {
        player.sendMessage(
                new TextComponent("Found " + blowBelow.asItem().getRegistryName().toString() + " in " +
                        "(" + blockPos.getX() + " , " + blockPos.getY() + ", " + blockPos.getZ() + ")"), player.getUUID()
        );
    }
    private boolean isValuableBlock(Block block) {
        return block == Blocks.COAL_ORE || block == Blocks.COPPER_ORE
                || block == Blocks.DIAMOND_ORE || block == Blocks.IRON_ORE;
    }



}
