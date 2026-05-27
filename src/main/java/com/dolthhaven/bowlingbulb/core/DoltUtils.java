package com.dolthhaven.bowlingbulb.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

@SuppressWarnings("removal")
public class DoltUtils {
    public static final String NIRVANA = "nirvana";

    public static Optional<Item> getPotentialItem(ResourceLocation location) {
        Item item = ForgeRegistries.ITEMS.getValue(location);
        return Optional.ofNullable(item == Items.AIR ? null : ForgeRegistries.ITEMS.getValue(location));
    }

    public static Optional<Item> getPotentialItem(String namespace, String path) {
        return getPotentialItem(new ResourceLocation(namespace, path));
    }
}
