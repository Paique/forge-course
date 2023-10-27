package com.paiique.mccourse.block;

import com.paiique.mccourse.McCourseMod;
import com.paiique.mccourse.block.custom.SpeedyBlock;
import com.paiique.mccourse.item.ModCreativeModeTab;
import com.paiique.mccourse.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, McCourseMod.MOD_ID);

    public static final RegistryObject<Block> COBALT_BLOCK = registryBlock("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()

            ),ModCreativeModeTab.COURSE_TAB);


    public static final RegistryObject<Block> RAW_COBALT_BLOCK = registryBlock("raw_cobalt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()

            ),ModCreativeModeTab.COURSE_TAB);

    public static final RegistryObject<Block> COBALT_ORE = registryBlock("cobalt_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ),ModCreativeModeTab.COURSE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = registryBlock("deepslate_cobalt_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ),ModCreativeModeTab.COURSE_TAB);

    public static final RegistryObject<Block> SPEEDY_BLOCK = registryBlock("speedy_block",
            () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()
            ),ModCreativeModeTab.COURSE_TAB);


    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
