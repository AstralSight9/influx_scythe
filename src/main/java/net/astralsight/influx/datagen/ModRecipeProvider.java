package net.astralsight.influx.datagen;

import net.astralsight.influx.Influx;
import net.astralsight.influx.block.ModBlocks;
import net.astralsight.influx.item.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> INFLUX_SMELTABLES = List.of(ModItems.CRYSTALLIZED_INFLUX.get(),
            ModBlocks.INFLUX_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, INFLUX_SMELTABLES, RecipeCategory.MISC, ModItems.INFLUX_SHARD.get(), 0.25f, 220, "influx_shard");
        oreBlasting(pWriter, INFLUX_SMELTABLES, RecipeCategory.MISC, ModItems.INFLUX_SHARD.get(), 0.25f, 110, "influx_shard");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INFLUX_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.INFLUX_SHARD.get())
                .unlockedBy(getHasName(ModItems.INFLUX_SHARD.get()), has(ModItems.INFLUX_SHARD.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTALLIZED_INFLUX_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.CRYSTALLIZED_INFLUX.get())
                .unlockedBy(getHasName(ModItems.CRYSTALLIZED_INFLUX.get()), has(ModItems.CRYSTALLIZED_INFLUX.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFLUX_SHARD.get())
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', ModItems.INFLUX_NUGGET.get())
                .unlockedBy(getHasName(ModItems.INFLUX_NUGGET.get()), has(ModItems.INFLUX_NUGGET.get()))
                .save(pWriter, new ResourceLocation(Influx.MOD_ID, "influx_shard_from_influx_nugget"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INFLUX_SHARD.get(), 9)
                .requires(ModBlocks.INFLUX_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.INFLUX_BLOCK.get()), has(ModBlocks.INFLUX_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CRYSTALLIZED_INFLUX.get(), 9)
                .requires(ModBlocks.CRYSTALLIZED_INFLUX_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CRYSTALLIZED_INFLUX_BLOCK.get()), has(ModBlocks.CRYSTALLIZED_INFLUX_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INFLUX_NUGGET.get(), 9)
                .requires(ModItems.INFLUX_SHARD.get())
                .unlockedBy(getHasName(ModItems.INFLUX_SHARD.get()), has(ModItems.INFLUX_SHARD.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAF.get(), 4)
                .requires(ItemTags.LEAVES)
                .unlockedBy("has_leaves", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ItemTags.LEAVES).build()))
                .save(pWriter, new ResourceLocation(Influx.MOD_ID, "leaf_from_leaves_tag"));
    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  Influx.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}