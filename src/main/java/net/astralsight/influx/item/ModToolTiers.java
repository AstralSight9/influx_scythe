package net.astralsight.influx.item;

import net.astralsight.influx.Influx;
import net.astralsight.influx.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier INFLUX = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2500, 6f, 4f, 12,
                    ModTags.Blocks.NEEDS_INFLUX_TOOL, () -> Ingredient.of(ModItems.INFLUX_SHARD.get())),
            new ResourceLocation(Influx.MOD_ID, "influx"), List.of(Tiers.NETHERITE), List.of());
}
