package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin {
    @ModifyReturnValue(method = "getCraftingRemainingItem", at = @At(value = "RETURN"))
    private Item BowlingBulb$NoMoreBowls(Item original) {
        if(original== Items.BOWL) return Items.AIR;
        return original;
    }
}
