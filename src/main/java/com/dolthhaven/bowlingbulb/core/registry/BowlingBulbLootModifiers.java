package com.dolthhaven.bowlingbulb.core.registry;

import com.dolthhaven.bowlingbulb.BowlingBulb;
import com.dolthhaven.bowlingbulb.common.loot.KillBowlsLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BowlingBulbLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister
            .create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, BowlingBulb.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> DROP_SELF = LOOT_MODIFIERS.register("bowling_bulb_eat", KillBowlsLootModifier.CODEC);
}
