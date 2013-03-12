package deathrat.mods.btbees;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import powercrystals.core.updater.IUpdateableMod;
import thermalexpansion.api.crafting.CraftingManagers;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import deathrat.mods.btbees.api.ICookingResult;
import deathrat.mods.btbees.blocks.BlockBoiler;
import deathrat.mods.btbees.blocks.BlockBoilerTank;
import deathrat.mods.btbees.blocks.BlockRicePlant;
import deathrat.mods.btbees.blocks.BlockSalt;
import deathrat.mods.btbees.blocks.BlockSteamer;
import deathrat.mods.btbees.blocks.BlockWok;
import deathrat.mods.btbees.entity.CheapBoat;
import deathrat.mods.btbees.events.BTBEvents;
import deathrat.mods.btbees.events.PlayerTracker;
import deathrat.mods.btbees.gui.BTBCreativeTab;
import deathrat.mods.btbees.gui.BTBGuiHandler;
import deathrat.mods.btbees.items.BTBFood;
import deathrat.mods.btbees.items.BoatItem;
import deathrat.mods.btbees.items.ItemBreadCrumbs;
import deathrat.mods.btbees.items.ItemRiceHusk;
import deathrat.mods.btbees.items.ItemRiceSeeds;
import deathrat.mods.btbees.items.ItemSalt;
import deathrat.mods.btbees.items.ItemSheepMeat;
import deathrat.mods.btbees.network.BTBConnectionHandler;
import deathrat.mods.btbees.network.ServerPacketHandler;
import deathrat.mods.btbees.proxy.CommonProxy;
import deathrat.mods.btbees.recipe.BTBFuelHandler;
import deathrat.mods.btbees.recipe.WokRecipes;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;
import deathrat.mods.btbees.tileentity.TileEntityRicePlant;
import deathrat.mods.btbees.tileentity.TileEntitySteamer;
import deathrat.mods.btbees.tileentity.TileEntityWok;
import deathrat.mods.btbees.updater.UpdateManager;

@Mod(modid = "btbees", name = BetterThanBees.modName, version = BetterThanBees.version, dependencies = "after:BuildCraft|Core;after:BuildCraft|Factory;after:BuildCraft|Energy;after:BuildCraft|Builders;after:BuildCraft|Transport;after:ThermalExpansion;required-after:PowerCrystalsCore")
@NetworkMod(serverSideRequired = true, clientSideRequired = true, channels = { "btbees" }, packetHandler = ServerPacketHandler.class, connectionHandler = BTBConnectionHandler.class)
public class BetterThanBees implements IUpdateableMod
{
	@Instance("BetterThanBees")
	public static BetterThanBees instance;

	@SidedProxy(clientSide = "deathrat.mods.btbees.proxy.ClientProxy", serverSide = "deathrat.mods.btbees.proxy.CommonProxy")
	public static CommonProxy proxy;

	public final static String modId = "BetterThanBees";
	public final static String version = "1.4.7R0.2.4";
	public final static String modName = "Better Than Bees";

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();

		MinecraftForge.EVENT_BUS.register(new BTBEvents());
		GameRegistry.registerPlayerTracker(new PlayerTracker());

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		initializeItemConfig(config);
		initializeBlockConfig(config);

		config.save();
	}

	private void initializeBlockConfig(Configuration config)
	{
		ricePlantID = config.getBlock("ricePlant", 3876).getInt();
		wokID = config.getBlock("wok", 3877).getInt();
		boilerID = config.getBlock("boiler", 3878).getInt();
		saltBlockID = config.getBlock("salt", 3879).getInt();
		steamerID = config.getBlock("steamer", 3880).getInt();
		boilerTankID = config.getBlock("boilerTank", 3881).getInt();
	}

	private void initializeItemConfig(Configuration config)
	{
		uncookedRiceID = config.getItem("uncookedRice", 8023).getInt();
		cookedRiceBallID = config.getItem("riceBall", 8024).getInt();
		cookedRiceBowlID = config.getItem("riceBowl", 8025).getInt();
		cookedRiceRollID = config.getItem("riceRoll", 8026).getInt();
		riceHuskID = config.getItem("riceHusk", 8027).getInt();
		saltItemID = config.getItem("itemSalt", 8028).getInt();
		sheepMeatID = config.getItem("sheepMeat", 8029).getInt();
		breadCrumbsID = config.getItem("breadCrumbs", 8030).getInt();
		boatItemID = config.getItem("boatItem", 8031).getInt();
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		instance = this;

		initalizeItems();
		initializeBlocks();
		initializeLanguageSetup();
		initializeRecipes();
		initializeCustomCreative();
		initializeGui();
		initializeEntities();

		TickRegistry.registerScheduledTickHandler(new UpdateManager(this), Side.CLIENT);

		proxy.init();
	}

	private void initializeEntities()
	{
		EntityRegistry.registerModEntity(CheapBoat.class, "CheapBoat", 1, this, 250, 1, true);

		// ModLoader.registerEntityID(CheapBoat.class, "CheapBoat",
		// ModLoader.getUniqueEntityId());

		LanguageRegistry.instance().addStringLocalization("entity.CheapBoat.name", "en_US", "Cheap Boat");
	}

	private void initializeCustomCreative()
	{
		customTab = new BTBCreativeTab(CreativeTabs.getNextID(), "btbTab");
		uncookedRice.setCreativeTab(customTab);
		riceHusk.setCreativeTab(customTab);
		cookedRiceBall.setCreativeTab(customTab);
		cookedRiceBowl.setCreativeTab(customTab);
		cookedRiceRoll.setCreativeTab(customTab);
		wok.setCreativeTab(customTab);
		boiler.setCreativeTab(customTab);
		steamer.setCreativeTab(customTab);
		saltBlock.setCreativeTab(customTab);
		sheepMeat.setCreativeTab(customTab);
		saltItem.setCreativeTab(customTab);
		breadCrumbs.setCreativeTab(customTab);
		boilerTank.setCreativeTab(customTab);
		boatItem.setCreativeTab(customTab);
	}

	private void initializeRecipes()
	{
		// GameRegistry.addSmelting(uncookedRiceID, new
		// ItemStack(cookedRiceBall), 2.0F);
		// WokRecipes.addRecipe(new Item[] {uncookedRice, uncookedRice,
		// sheepMeat}, (ICookingResult) cookedRiceRoll);
		WokRecipes.addRecipe((ICookingResult) cookedRiceBall, new Object[] { uncookedRice });
		FurnaceRecipes.smelting().addSmelting(sheepMeat.itemID, 0, new ItemStack(sheepMeat, 1, 1), 0.0F);
		FurnaceRecipes.smelting().addSmelting(sheepMeat.itemID, 2, new ItemStack(sheepMeat, 1, 3), 0.0F);
		GameRegistry.addShapelessRecipe(new ItemStack(uncookedRice, 1), new ItemStack(this.riceHusk));
		GameRegistry.registerFuelHandler(new BTBFuelHandler());
	}

	private void initializeLanguageSetup()
	{
		LanguageRegistry.addName(riceHusk, "Rice Husk");
		LanguageRegistry.addName(uncookedRice, "Rice");
		LanguageRegistry.addName(cookedRiceBall, "Rice Ball");
		LanguageRegistry.addName(cookedRiceBowl, "Bowl of Rice");
		LanguageRegistry.addName(cookedRiceRoll, "California Roll");
		LanguageRegistry.addName(saltItem, "Salt");
		LanguageRegistry.addName(boatItem, "Cheap Boat");
		LanguageRegistry.addName(wok, "Wok");
		LanguageRegistry.addName(boiler, "Boiler");
		LanguageRegistry.addName(saltBlock, "Salt");
		LanguageRegistry.addName(steamer, "Steamer");
		LanguageRegistry.addName(new ItemStack(sheepMeat, 1, 0), "Raw Mutton");
		LanguageRegistry.addName(new ItemStack(sheepMeat, 1, 1), "Cooked Mutton");
		LanguageRegistry.addName(new ItemStack(sheepMeat, 1, 2), "Raw Lamb");
		LanguageRegistry.addName(new ItemStack(sheepMeat, 1, 3), "Cooked Lamb");
		LanguageRegistry.addName(breadCrumbs, "Bread Crumbs");
		LanguageRegistry.addName(boilerTank, "Boiler Tank");

	}

	private void initializeBlocks()
	{
		ricePlant = new BlockRicePlant(ricePlantID, 0);
		GameRegistry.registerBlock(ricePlant, "ricePlant");
		GameRegistry.registerTileEntity(TileEntityRicePlant.class, "RicePlant");

		wok = new BlockWok(wokID, Material.iron);
		GameRegistry.registerBlock(wok, "wok");
		GameRegistry.registerTileEntity(TileEntityWok.class, "Wok");

		boiler = new BlockBoiler(boilerID, Material.iron);
		GameRegistry.registerBlock(boiler, "Boiler");
		GameRegistry.registerTileEntity(TileEntityBoiler.class, "Boiler");

		steamer = new BlockSteamer(steamerID, Material.iron);
		GameRegistry.registerBlock(steamer, "Steamer");
		GameRegistry.registerTileEntity(TileEntitySteamer.class, "Steamer");

		saltBlock = new BlockSalt(saltBlockID);
		GameRegistry.registerBlock(saltBlock, "Salt");

		boilerTank = new BlockBoilerTank(boilerTankID, Material.iron);
		GameRegistry.registerBlock(boilerTank, "Boiler Tank");
		GameRegistry.registerTileEntity(TileEntityBoilerTank.class, "Boiler Tank");
	}

	private void initalizeItems()
	{
		cookedRiceBowl = new BTBFood(cookedRiceBowlID, 4, 5, false).setIconIndex(0).setItemName("riceBowl");
		((BTBFood) cookedRiceBowl).setHasBowl(true);
		cookedRiceBall = new BTBFood(cookedRiceBallID, 4, 3, false).setIconIndex(1).setItemName("riceBall");
		cookedRiceRoll = new BTBFood(cookedRiceRollID, 4, 7, false).setIconIndex(2).setItemName("riceRoll");
		uncookedRice = new ItemRiceSeeds(uncookedRiceID, ricePlantID).setIconIndex(3).setItemName("uncookedRice");
		riceHusk = new ItemRiceHusk(riceHuskID).setIconIndex(4).setItemName("riceHusk");
		saltItem = new ItemSalt(saltItemID).setIconIndex(6).setItemName("itemSalt");
		sheepMeat = new ItemSheepMeat(sheepMeatID, 4, 7, true);
		breadCrumbs = new ItemBreadCrumbs(breadCrumbsID).setIconIndex(9).setItemName("breadCrumbs");
		boatItem = new BoatItem(boatItemID).setIconIndex(10).setItemName("boatItem");

		MinecraftForge.addGrassSeed(new ItemStack(uncookedRice), 8);
	}

	private CreativeTabs getCustomCreativeTab()
	{
		return this.customTab;
	}

	private void initializeGui()
	{
		NetworkRegistry.instance().registerGuiHandler(this, new BTBGuiHandler());
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
		if (CraftingManagers.pulverizerManager != null)
			CraftingManagers.pulverizerManager.addRecipe(100, new ItemStack(Item.bread), new ItemStack(breadCrumbs, 1), false);
	}

	public final static String getTerrainTextures()
	{
		return terrainTextures;
	}

	public final static String getItemTextures()
	{
		return itemTextures;
	}

	public final static String getResourcesPath()
	{
		return resourcesPath;
	}

	// Texture files
	public final static String terrainTextures = getResourcesPath() + "btb_terrain.png";
	public final static String itemTextures = getResourcesPath() + "btb_items.png";
	public final static String resourcesPath = "/deathrat/mods/btbees/resources/";

	// Item IDs
	public static int riceHuskID;
	public static int uncookedRiceID;
	public static int cookedRiceBallID;
	public static int cookedRiceBowlID;
	public static int cookedRiceRollID;
	public static int pepperSeedID;
	public static int saltItemID;
	public static int sheepMeatID;
	public static int breadCrumbsID;

	// Block IDs
	public static int ricePlantID;
	public static int pepperPlantID;
	public static int wokID;
	public static int boilerID;
	public static int steamerID;
	public static int saltBlockID;
	public static int boilerTankID;
	public static int boatItemID;

	// Items
	public static Item riceHusk;
	public static Item uncookedRice;
	public static Item cookedRiceBall;
	public static Item cookedRiceBowl;
	public static Item cookedRiceRoll;
	public static Item pepperSeeds;
	public static Item saltItem;
	public static Item sheepMeat;
	public static Item breadCrumbs;
	public static Item boatItem;

	// Blocks
	public static Block ricePlant;
	public static Block pepperPlant;
	public static Block wok;
	public static Block boiler;
	public static Block steamer;
	public static Block saltBlock;
	public static Block boilerTank;

	// Creative Tabs
	public static CreativeTabs customTab;

	// World Generation
	// public static WorldGen worldGen = new WorldGen();

	@Override
	public String getModId()
	{
		return modId;
	}

	@Override
	public String getModName()
	{
		return modName;
	}

	@Override
	public String getModFolder()
	{
		return "Better-Than-Bees";
	}

	@Override
	public String getModVersion()
	{
		return version;
	}
}
