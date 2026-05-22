package com.dolthhaven.bowlingbulb.core.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerGameModeMixin {
    @Shadow public abstract InteractionResult useItemOn(ServerPlayer p_9266_, Level p_9267_, ItemStack p_9268_, InteractionHand p_9269_, BlockHitResult p_9270_);

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void DoltModHow$sex(ServerPlayer player, Level level, ItemStack stack, InteractionHand hand, BlockHitResult result, CallbackInfoReturnable<InteractionResult> cir) {
//        if (hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
//            BlockState state = level.getBlockState(result.getBlockPos());
//            if (state.getBlock() instanceof FeastBlock) {
//                player.setItemInHand(hand, new ItemStack(Items.BOWL));
//                InteractionResult interactionResult = useItemOn(player, level, stack, hand, result);
//                cir.setReturnValue(interactionResult);
//                if (ItemStack.isSameItemSameTags(player.getItemInHand(hand), new ItemStack(Items.BOWL)))
//                    player.setItemInHand(hand, ItemStack.EMPTY);
//            }
//        }
    }
}
