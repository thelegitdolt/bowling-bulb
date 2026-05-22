package com.dolthhaven.bowlingbulb;

import com.dolthhaven.bowlingbulb.core.registry.BowlingBulbLootModifiers;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@SuppressWarnings("removal")
@Mod(BowlingBulb.MOD_ID)
public class BowlingBulb {
    public static final String MOD_ID = "bowling_bulb";
    public static final Logger LOGGER = LogUtils.getLogger();
    public BowlingBulb() {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        IEventBus bus = context.getModEventBus();
        BowlingBulbLootModifiers.LOOT_MODIFIERS.register(bus);
    }
}
