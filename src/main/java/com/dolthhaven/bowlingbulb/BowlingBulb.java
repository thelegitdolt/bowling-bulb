package com.dolthhaven.bowlingbulb;

import com.dolthhaven.bowlingbulb.core.DoltUtils;
import com.dolthhaven.bowlingbulb.core.registry.BowlingBulbLootModifiers;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.registry.ModItems;

@SuppressWarnings("removal")
@Mod(BowlingBulb.MOD_ID)
public class BowlingBulb {
    public static final String MOD_ID = "bowling_bulb";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BowlingBulb() {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        IEventBus bus = context.getModEventBus();

        bus.addListener(this::commonSetup);
        BowlingBulbLootModifiers.LOOT_MODIFIERS.register(bus);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ForgeRegistries.ITEMS.getValues().forEach(item -> {
                if (item.craftingRemainingItem == Items.BOWL || item.craftingRemainingItem == Items.GLASS_BOTTLE) {
                    item.craftingRemainingItem = Items.AIR;
                }
            });
            ModItems.COOKING_POT.get().asItem().maxStackSize = 64;
            DoltUtils.getPotentialItem("dungeonsdelight", "monster_pot").ifPresent(pot -> {
                pot.maxStackSize = 64;
            });
        });
    }
}
