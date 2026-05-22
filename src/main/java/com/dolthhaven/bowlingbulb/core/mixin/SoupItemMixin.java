package com.dolthhaven.bowlingbulb.core.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BowlFoodItem.class, priority = 0)
public class SoupItemMixin extends Item {
    public SoupItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void BowlingBulb$NoNeedToGiveBowlBackThankYou(ItemStack stack, Level level, LivingEntity living, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(super.finishUsingItem(stack, level, living));
    }
}
