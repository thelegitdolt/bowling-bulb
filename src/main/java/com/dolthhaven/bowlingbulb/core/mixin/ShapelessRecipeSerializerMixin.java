package com.dolthhaven.bowlingbulb.core.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;


@Mixin(ShapelessRecipe.Serializer.class)
public class ShapelessRecipeSerializerMixin {
    @Inject(method = "itemsFromJson", at = @At("HEAD"))
    private static void BowlingBulb$RemoveBowlsFromShapelessRecipes(JsonArray ingredientArray, CallbackInfoReturnable<NonNullList<Ingredient>> cir) {
        for (Iterator<JsonElement> iter = ingredientArray.iterator(); iter.hasNext();) {
            JsonElement element = iter.next();
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has("item") && ShapedRecipe.itemFromJson(element.getAsJsonObject()) == Items.BOWL) {
                    iter.remove();
                }

            }
        }
    }
}
