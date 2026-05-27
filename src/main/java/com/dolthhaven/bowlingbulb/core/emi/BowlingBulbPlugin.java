package com.dolthhaven.bowlingbulb.core.emi;

import com.dolthhaven.bowlingbulb.core.DoltUtils;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.Comparison;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

@SuppressWarnings("removal")
@EmiEntrypoint
public class BowlingBulbPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.removeRecipes(new ResourceLocation("suspicious_stew"));
        emiRegistry.addRecipe(new NewSusStewRecipe(new ResourceLocation("bowling_bulb", "super_sus_stew")));
        emiRegistry.removeRecipes(recipe -> {
            ResourceLocation id = recipe.getId();
            if (id == null) return false;
            else return id.toString().contains("nirvana:herbal_salve/") || id.toString().contains("nirvana:suspicious_pipe/");
        });


        DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "old_pipe").ifPresent(pipe -> {
            Item susPipe = DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "suspicious_pipe").orElseThrow();
            emiRegistry.setDefaultComparison(susPipe, Comparison.DEFAULT_COMPARISON);
            emiRegistry.addRecipe(new SuspiciousRecipe(6, 1, pipe, susPipe, new ResourceLocation("bowling_bulb", "john_pipe")));
        });

        DoltUtils.getPotentialItem(DoltUtils.NIRVANA, "herbal_salve").ifPresent(salve -> {
            emiRegistry.setDefaultComparison(salve, Comparison.DEFAULT_COMPARISON);
            emiRegistry.addRecipe(new SuspiciousRecipe(2, 2, Items.AIR, salve, new ResourceLocation("bowling_bulb", "salve")));
        });
    }
}
