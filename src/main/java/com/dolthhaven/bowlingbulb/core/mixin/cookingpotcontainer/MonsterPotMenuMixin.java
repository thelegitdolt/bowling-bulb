package com.dolthhaven.bowlingbulb.core.mixin.cookingpotcontainer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Pseudo
@Mixin(targets = "net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotMenu")
public class MonsterPotMenuMixin {
    @ModifyArgs(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/yirmiri/dungeonsdelight/common/block/entity/MonsterPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
        at = @At(value = "INVOKE", target = "Lnet/yirmiri/dungeonsdelight/common/block/entity/container/MonsterPotResultSlot;<init>(Lnet/minecraft/world/entity/player/Player;Lnet/yirmiri/dungeonsdelight/common/block/entity/MonsterPotBlockEntity;Lnet/minecraftforge/items/IItemHandler;III)V"))
    private void BowlingBulb$ResultSlotMove(Args args) {
        args.set(5, 26);
    }

    @ModifyArgs(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/yirmiri/dungeonsdelight/common/block/entity/MonsterPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/entity/container/CookingPotMealSlot;<init>(Lnet/minecraftforge/items/IItemHandler;III)V"))
    private void BowlingBulb$MealSlotYeet(Args args) {
        args.set(3, Integer.MAX_VALUE);
    }

    @WrapOperation(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/yirmiri/dungeonsdelight/common/block/entity/MonsterPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(value = "INVOKE", target = "Lnet/yirmiri/dungeonsdelight/common/block/entity/container/MonsterPotMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"))
    private Slot BowlingBulb$ContainerSlotYeet(MonsterPotMenu instance, Slot slot, Operation<Slot> original) {
        if (slot.y == 55 && slot.x == 92) {
            return original.call(instance, new SlotItemHandler(instance.inventory, 7, Integer.MAX_VALUE, Integer.MAX_VALUE));
        } return original.call(instance, slot);
    }

    @WrapOperation(method = "quickMoveStack",
            at = @At(value = "INVOKE", target = "Lnet/yirmiri/dungeonsdelight/common/block/entity/container/MonsterPotMenu;moveItemStackTo(Lnet/minecraft/world/item/ItemStack;IIZ)Z"))
    private boolean BowlingBulb$ContainerSlotYeet(MonsterPotMenu instance, ItemStack stack, int from, int to, boolean b, Operation<Boolean> original) {
        if (from <= 7 && 7 < to) return false;
        return original.call(instance, stack, from, to, b);
    }
}
