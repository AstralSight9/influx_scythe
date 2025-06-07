package net.astralsight.influx.item;

import net.astralsight.influx.Influx;
import net.astralsight.influx.custom.FuelItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Influx.MOD_ID);

public static final RegistryObject<Item> CRYSTALLIZED_INFLUX = ITEMS.register("crystallized_influx",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFLUX_SHARD = ITEMS.register("influx_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFLUX_NUGGET = ITEMS.register("influx_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_IRON_INGOT = ITEMS.register("black_iron_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFLUX_APPLE = ITEMS.register("influx_apple",
            () -> new Item(new Item.Properties().food(ModFoods.INFLUX_APPLE).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> INFLUX_LEAF = ITEMS.register("influx_leaf",
            () -> new Item(new Item.Properties().food(ModFoods.INFLUX_LEAF)));

    public static final RegistryObject<Item> LEAF = ITEMS.register("leaf",
            () -> new Item(new Item.Properties().food(ModFoods.LEAF)));

    public static final RegistryObject<Item> BLACK_IRON_SCYTHE = ITEMS.register("black_iron_scythe",
            () -> new SwordItem(ModToolTiers.INFLUX,6, -2, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_IRON_ANCHOR = ITEMS.register("black_iron_anchor",
            () -> new PickaxeItem(ModToolTiers.INFLUX,0, -2, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_IRON_CLEAVER = ITEMS.register("black_iron_cleaver",
            () -> new AxeItem(ModToolTiers.INFLUX,8, -3, new Item.Properties()));

    public static final RegistryObject<Item> BLACK_IRON_CLAWS = ITEMS.register("black_iron_claws",
            () -> new ShovelItem(ModToolTiers.INFLUX,0, -2, new Item.Properties()));

    public static  void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}