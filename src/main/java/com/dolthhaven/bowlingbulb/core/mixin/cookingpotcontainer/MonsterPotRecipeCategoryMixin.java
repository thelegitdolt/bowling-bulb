package com.dolthhaven.bowlingbulb.core.mixin.cookingpotcontainer;

import mezz.jei.api.recipe.RecipeIngredientRole;
import net.yirmiri.dungeonsdelight.integration.jei.category.MonsterPotRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MonsterPotRecipeCategory.class)
public class MonsterPotRecipeCategoryMixin {
    @ModifyArgs(method = "setRecipe(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;Lnet/yirmiri/dungeonsdelight/common/block/entity/container/MonsterPotRecipe;Lmezz/jei/api/recipe/IFocusGroup;)V",
            at = @At(value = "INVOKE", target = "Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;addSlot(Lmezz/jei/api/recipe/RecipeIngredientRole;II)Lmezz/jei/api/gui/builder/IRecipeSlotBuilder;"), remap = false)
    private void sex(Args args) {
        int x = args.get(1);
        int y = args.get(2);
        if (args.get(0) == RecipeIngredientRole.CATALYST) {
            args.set(2, Integer.MAX_VALUE);
        } else if (x == 95 && y == 39) {
            args.set(2, Integer.MAX_VALUE);
        }
    }
}
