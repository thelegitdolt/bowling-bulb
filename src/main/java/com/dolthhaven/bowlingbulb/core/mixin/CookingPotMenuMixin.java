package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

@Pseudo
@Mixin(value = CookingPotMenu.class, targets = "net.yirmiri.dungeonsdelight.common.block.monster_pot.MonsterPotMenu")
public class CookingPotMenuMixin {
    @ModifyArgs(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/entity/container/CookingPotResultSlot;<init>(Lnet/minecraft/world/entity/player/Player;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;Lnet/minecraftforge/items/IItemHandler;III)V"))
    private void BowlingBulb$ResultSlotMove(Args args) {
        args.set(5, 26);
    }

    @ModifyArgs(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/entity/container/CookingPotMealSlot;<init>(Lnet/minecraftforge/items/IItemHandler;III)V"))
    private void BowlingBulb$MealSlotYeet(Args args) {
        args.set(3, Integer.MAX_VALUE);
    }

    @WrapOperation(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lvectorwing/farmersdelight/common/block/entity/CookingPotBlockEntity;Lnet/minecraft/world/inventory/ContainerData;)V",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/entity/container/CookingPotMenu;addSlot(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;"))
    private Slot BowlingBulb$ContainerSlotYeet(CookingPotMenu instance, Slot slot, Operation<Slot> original) {
        if (slot.y == 55 && slot.x == 92) {
            return original.call(instance, new SlotItemHandler(instance.inventory, 7, Integer.MAX_VALUE, Integer.MAX_VALUE));
        } return original.call(instance, slot);
    }

    @WrapOperation(method = "quickMoveStack",
            at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/entity/container/CookingPotMenu;moveItemStackTo(Lnet/minecraft/world/item/ItemStack;IIZ)Z"))
    private boolean BowlingBulb$ContainerSlotYeet(CookingPotMenu instance, ItemStack stack, int from, int to, boolean b, Operation<Boolean> original) {
        if (from <= 7 && 7 < to) return false;
        return original.call(instance, stack, from, to, b);
    }
}
