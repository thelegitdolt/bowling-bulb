package com.dolthhaven.bowlingbulb.core.mixin.nirvana;

import com.dolthhaven.bowlingbulb.core.DoltUtils;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import galena.nirvana.forge.compat.NirvanaForgeJeiPlugin;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(NirvanaForgeJeiPlugin.class)
public class NirvanaForgeJeiPluginMixin {
    @WrapWithCondition(method = "registerItemSubtypes", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/ISubtypeRegistration;registerSubtypeInterpreter(Lnet/minecraft/world/item/Item;Lmezz/jei/api/ingredients/subtypes/IIngredientSubtypeInterpreter;)V"), remap = false)
    private boolean sex(ISubtypeRegistration instance, Item item, IIngredientSubtypeInterpreter<ItemStack> interpreter) {
        if (item == DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "herbal_salve").orElse(Items.AIR)) return false;
        if (item == DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "suspicious_pipe").orElse(Items.AIR)) return false;
        return true;
    }
}
