package com.dolthhaven.bowlingbulb.core.emi;

import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.GeneratedSlotWidget;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NewSusStewRecipe extends EmiPatternCraftingRecipe {
    static final List<Item> FLOWERS = ForgeRegistries.ITEMS.getValues().stream().filter((i) -> {
        if (i instanceof BlockItem bi) {
            return bi.getBlock() instanceof FlowerBlock;
        }

        return false;
    }).toList();

    public NewSusStewRecipe(ResourceLocation id) {
        super(List.of(EmiStack.of(Items.BOWL), EmiStack.of(Items.RED_MUSHROOM), EmiStack.of(Items.BROWN_MUSHROOM),
                EmiIngredient.of(FLOWERS.stream().map(EmiStack::of).collect(Collectors.toList()))),
                EmiStack.of(Items.SUSPICIOUS_STEW), id);
    }

    public SlotWidget getInputWidget(int slot, int x, int y) {
        if (slot == 0) {
            return new SlotWidget(EmiStack.of(Items.RED_MUSHROOM), x, y);
        } else if (slot == 1) {
            return new SlotWidget(EmiStack.of(Items.BROWN_MUSHROOM), x, y);
        } else {
            return slot == 3 ? new GeneratedSlotWidget((r) -> EmiStack.of(getFlower(r)), this.unique, x, y) : new SlotWidget(EmiStack.EMPTY, x, y);
        }
    }

    public SlotWidget getOutputWidget(int x, int y) {
        return new GeneratedSlotWidget((r) -> {
            FlowerBlock block = (FlowerBlock)((BlockItem) getFlower(r)).getBlock();
            ItemStack stack = new ItemStack(Items.SUSPICIOUS_STEW);
            SuspiciousStewItem.saveMobEffect(stack, block.getSuspiciousEffect(), block.getEffectDuration());
            return EmiStack.of(stack);
        }, this.unique, x, y);
    }

    static Item getFlower(Random random) {
        return FLOWERS.get(random.nextInt(FLOWERS.size()));
    }
}
