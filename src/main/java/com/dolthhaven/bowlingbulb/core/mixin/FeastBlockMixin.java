package com.dolthhaven.bowlingbulb.core.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.FeastBlock;

@Mixin(FeastBlock.class)
public class FeastBlockMixin {
    @WrapWithCondition(method = "takeServing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private boolean BowlingBulb$FeastMessage(Player instance, Component pChatComponent, boolean pActionBar) {
        return false;
    }
}
