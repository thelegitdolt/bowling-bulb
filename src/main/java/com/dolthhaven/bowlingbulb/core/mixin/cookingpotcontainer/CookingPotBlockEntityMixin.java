package com.dolthhaven.bowlingbulb.core.mixin.cookingpotcontainer;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

import java.util.Arrays;
import java.util.Map;


@Pseudo
@Mixin(value = CookingPotBlockEntity.class, targets = "net.yirmiri.dungeonsdelight.common.block.entity.MonsterPotBlockEntity")
public class CookingPotBlockEntityMixin {
    @ModifyArg(method = "processCooking", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/ItemStackHandler;setStackInSlot(ILnet/minecraft/world/item/ItemStack;)V"), remap = false)
    private int BowlingBulb$targetResultSlot(int slot) {
        return slot == 6 ? 8 : slot;
    }

    @ModifyArg(method = "processCooking", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/items/ItemStackHandler;getStackInSlot(I)Lnet/minecraft/world/item/ItemStack;"), remap = false)
    private int BowlingBulb$getFromResultSlot(int slot) {
        return slot == 6 ? 8 : slot;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Map;ofEntries([Ljava/util/Map$Entry;)Ljava/util/Map;"))
    private static <K, V> Map.Entry<? extends K, ? extends V>[] BowlingBulb$RemoveBowlOverrides(Map.Entry<? extends K, ? extends V>[] entries) {
        return Arrays.stream(entries).filter(entry -> entry.getValue() instanceof Item item && item != Items.BOWL).toArray(Map.Entry[]::new);
    }
}
