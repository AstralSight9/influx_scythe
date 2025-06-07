package net.astralsight.influx.datagen;

import net.astralsight.influx.Influx;
import net.astralsight.influx.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Influx.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.INFLUX_SHARD);
        simpleItem(ModItems.CRYSTALLIZED_INFLUX);
        simpleItem(ModItems.LEAF);
        simpleItem(ModItems.INFLUX_LEAF);
        simpleItem(ModItems.INFLUX_APPLE);
        simpleItem(ModItems.INFLUX_NUGGET);
        simpleItem(ModItems.BLACK_IRON_INGOT);


        handheldItem(ModItems.BLACK_IRON_ANCHOR);
        handheldItem(ModItems.BLACK_IRON_CLEAVER);
        handheldItem(ModItems.BLACK_IRON_CLAWS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Influx.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Influx.MOD_ID, "item/" + item.getId().getPath()));
    }
}
