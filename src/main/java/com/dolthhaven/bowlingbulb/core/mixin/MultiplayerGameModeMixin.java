package com.dolthhaven.bowlingbulb.core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.FeastBlock;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiplayerGameModeMixin {
    @Shadow protected abstract InteractionResult performUseItemOn(LocalPlayer p_233747_, InteractionHand p_233748_, BlockHitResult p_233749_);

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "performUseItemOn", at = @At("HEAD"), cancellable = true)
    private void sex(LocalPlayer player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
            ClientLevel level = this.minecraft.level;
            BlockState state = level.getBlockState(hitResult.getBlockPos());
            if (state.getBlock() instanceof FeastBlock feastBlock) {
                player.setItemInHand(hand, new ItemStack(Items.BOWL));
                InteractionResult interactionResult = performUseItemOn(player, hand, hitResult);
                if (player.isCreative()) player.setItemInHand(hand, ItemStack.EMPTY);
                cir.setReturnValue(interactionResult);
            }
        }
    }
}
