package com.dolthhaven.bowlingbulb.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(CookingPotBlockEntity.class)
public class CookingPotBlockEntityMixin {
    @ModifyArg(method = "processCooking", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/ItemStackHandler;setStackInSlot(ILnet/minecraft/world/item/ItemStack;)V"), remap = false)
    private int BowlingBulb$targetResultSlot(int slot) {
        return slot == 6 ? 8 : slot;
    }

    @ModifyArg(method = "processCooking", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/ItemStackHandler;getStackInSlot(I)Lnet/minecraft/world/item/ItemStack;"), remap = false)
    private int BowlingBulb$getFromResultSlot(int slot) {
        return slot == 6 ? 8 : slot;
    }
}
