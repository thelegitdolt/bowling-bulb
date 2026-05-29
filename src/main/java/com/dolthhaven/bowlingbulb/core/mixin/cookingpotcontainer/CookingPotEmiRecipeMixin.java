package com.dolthhaven.bowlingbulb.core.mixin.cookingpotcontainer;

import dev.emi.emi.api.stack.EmiStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vectorwing.farmersdelight.integration.emi.recipe.CookingPotEmiRecipe;

@Mixin(CookingPotEmiRecipe.class)
public class CookingPotEmiRecipeMixin {
    @Shadow @Final private EmiStack container;

    @ModifyArgs(method = "addWidgets", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/integration/emi/recipe/CookingPotEmiRecipe;addSlot(Ldev/emi/emi/api/widget/WidgetHolder;Ldev/emi/emi/api/stack/EmiIngredient;II)Ldev/emi/emi/api/widget/SlotWidget;"), remap = false)
    private void BowlingBulb$RemoveOutputSlotFromEmiPlugin(Args args) {
        int x = args.get(2);
        int y = args.get(3);
        if (y == 38 && x == 94) {
            args.set(3, Integer.MAX_VALUE);
        } else if (args.get(1) == this.container) {
            args.set(3, Integer.MAX_VALUE);
        }
    }
}
