package com.dolthhaven.bowlingbulb.core.emi;

import com.dolthhaven.bowlingbulb.core.DoltUtils;
import com.google.common.collect.ImmutableList;
import dev.emi.emi.api.recipe.EmiPatternCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.GeneratedSlotWidget;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.List;
import java.util.stream.Collectors;

public class SuspiciousRecipe extends EmiPatternCraftingRecipe {
    private final int flowerCount;
    private final int weedCount;
    private final Item base;

    public SuspiciousRecipe(int flowerCount, int weedCount, Item base, Item result, ResourceLocation id) {
        super(makeIngredientList(flowerCount, weedCount, base), EmiStack.of(result), id);
        this.flowerCount = flowerCount;
        this.weedCount = weedCount;
        this.base = base;
    }

    @Override
    public SlotWidget getInputWidget(int slot, int x, int y) {
        if (slot < flowerCount) {
            return new GeneratedSlotWidget((r) -> EmiStack.of(NewSusStewRecipe.getFlower(r)), this.unique, x, y);
        } else if (slot < flowerCount + weedCount) {
            return new SlotWidget(EmiStack.of(DoltUtils.getPotentialItem("nirvana", "weed").orElseThrow()), x, y);
        } else if (slot < flowerCount + weedCount + 1 && base != Items.AIR) {
            return new SlotWidget(EmiStack.of(base), x, y);
        }  else {
            return new SlotWidget(EmiStack.EMPTY, x, y);
        }
    }

    @Override
    public SlotWidget getOutputWidget(int x, int y) {
        return new GeneratedSlotWidget((r) -> {
            FlowerBlock block = (FlowerBlock)((BlockItem) NewSusStewRecipe.getFlower(r)).getBlock();
            ItemStack stack = this.output.getItemStack();
            SuspiciousStewItem.saveMobEffect(stack, block.getSuspiciousEffect(), block.getEffectDuration());
            return EmiStack.of(stack);
        }, this.unique, x, y);
    }

    private static List<EmiIngredient> makeIngredientList(int flowerCount, int weedCount, Item base) {
        ImmutableList.Builder<EmiIngredient> listBuilder = new ImmutableList.Builder<>();
        EmiIngredient flowerList = EmiIngredient.of(NewSusStewRecipe.FLOWERS.stream().map((i) -> EmiStack.of(new ItemStack(i))).collect(Collectors.toList()));
        for (int i = 0; i < flowerCount; i++) {
            listBuilder.add(flowerList);
        }
        EmiStack weed = EmiStack.of(DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "weed").orElseThrow());
        for (int i = 0; i < weedCount; i++) {
            listBuilder.add(weed);
        }
        listBuilder.add(EmiStack.of(base));
        return listBuilder.build();
    }
}
