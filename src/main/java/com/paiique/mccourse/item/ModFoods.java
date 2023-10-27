package com.paiique.mccourse.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CARMELLO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 9), 0.1f)
            .build();
}
