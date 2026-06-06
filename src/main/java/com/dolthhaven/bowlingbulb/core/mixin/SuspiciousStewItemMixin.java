package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SuspiciousStewItem.class)
public class SuspiciousStewItemMixin {
    @ModifyReturnValue(method = "finishUsingItem", at = @At("RETURN"))
    private ItemStack sex(ItemStack original, @Local(ordinal = 1) ItemStack ret) {
        return ret;
    }
}
