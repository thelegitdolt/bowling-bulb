package com.dolthhaven.bowlingbulb.core.mixin.cookingpotcontainer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vectorwing.farmersdelight.integration.emi.recipe.CookingPotEmiRecipe;

@Mixin(CookingPotEmiRecipe.class)
public class CookingPotEmiRecipeMixin {
    @ModifyArgs(method = "addWidgets", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/integration/emi/recipe/CookingPotEmiRecipe;addSlot(Ldev/emi/emi/api/widget/WidgetHolder;Ldev/emi/emi/api/stack/EmiIngredient;II)Ldev/emi/emi/api/widget/SlotWidget;"), remap = false)
    private void BowlingBulb$RemoveOutputSlotFromEmiPlugin(Args args) {
        if ((Integer) args.get(3) == 38 && (Integer) args.get(2) == 94) {
            args.set(3, Integer.MAX_VALUE);
        }
    }
}
