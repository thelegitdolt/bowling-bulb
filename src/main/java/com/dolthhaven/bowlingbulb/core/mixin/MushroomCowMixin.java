package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.animal.MushroomCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MushroomCow.class)
public class MushroomCowMixin {
    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z")
    @Definition(id = "SMALL_FLOWERS", field = "Lnet/minecraft/tags/ItemTags;SMALL_FLOWERS:Lnet/minecraft/tags/TagKey;")
    @Expression("?.is(SMALL_FLOWERS)")
    @ModifyExpressionValue(method = "mobInteract", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean BowlingBulb$NoFlowers(boolean original) {
        return false;
    }
}
