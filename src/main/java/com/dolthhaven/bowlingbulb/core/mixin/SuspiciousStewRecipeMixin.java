package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.SuspiciousStewRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SuspiciousStewRecipe.class)
public class SuspiciousStewRecipeMixin {
    @WrapMethod(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z")
    private boolean sex(CraftingContainer pInv, Level pLevel, Operation<Boolean> original) {
        boolean red = false, brown = false, flower = false;
        for (ItemStack stack : pInv.getItems()) {
            if (stack.isEmpty()) continue;

            if (stack.is(Items.RED_MUSHROOM) && !red) {
                red = true;
            } else if (stack.is(Items.BROWN_MUSHROOM) && !brown) {
                brown = true;
            } else if (stack.is(ItemTags.SMALL_FLOWERS) && !flower) {
                flower = true;
            } else {
                return false;
            }
        }
        return red & brown & flower;
    }
}
