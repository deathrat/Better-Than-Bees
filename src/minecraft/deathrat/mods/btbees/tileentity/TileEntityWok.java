package deathrat.mods.btbees.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.api.ICookingResult;
import deathrat.mods.btbees.network.ServerPacketHandler;
import deathrat.mods.btbees.recipe.WokRecipes;

public class TileEntityWok extends TileEntity implements IInventory
{
	private ItemStack[] inv;
	private int fireLevel;
	public int furnaceCookTime;
	public int furnaceBurnTime;
	public int currentItemBurnTime;

	public TileEntityWok()
	{
		inv = new ItemStack[8];
	}

	@Override
	public void updateEntity()
	{
		boolean isCooking = furnaceBurnTime > 0;
		boolean hasChanged = false;

		if (isCooking)
		{
			--furnaceBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.furnaceBurnTime == 0 && canCook())
			{
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime(this.inv[1]);

				if (furnaceBurnTime > 0)
				{
					hasChanged = true;

					if (inv[1] != null)
					{
						--inv[1].stackSize;

						if (inv[1].stackSize == 0)
						{
							inv[1] = inv[1].getItem().getContainerItemStack(inv[1]);
						}
					}
				}
			}

			if (isBurning() && canCook())
			{
				++furnaceCookTime;

				if (furnaceCookTime == 200)
				{
					furnaceCookTime = 0;
					cookIngredient();
					hasChanged = true;
				}
			}
			else
			{
				furnaceCookTime = 0;
			}
			if (!canCook() && inv[3] != null)
			{
				updateResult();
				hasChanged = true;
			}

			if (isCooking != furnaceBurnTime > 0)
			{
				hasChanged = true;
			}

			ServerPacketHandler.sendWokUpdate(this, this.fireLevel);
		}

		if (hasChanged)
		{
			onInventoryChanged();
		}
	}

	public static int getItemBurnTime(ItemStack par0ItemStack)
	{
		if (par0ItemStack == null)
		{
			return 0;
		}
		else
		{
			int var1 = par0ItemStack.getItem().itemID;
			Item var2 = par0ItemStack.getItem();

			if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
			{
				Block var3 = Block.blocksList[var1];

				if (var3 == Block.woodSingleSlab)
				{
					return 150;
				}

				if (var3.blockMaterial == Material.wood)
				{
					return 300;
				}
			}

			if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
				return 200;
			if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD"))
				return 200;
			if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD"))
				return 200;
			if (var1 == Item.stick.itemID)
				return 100;
			if (var1 == Item.coal.itemID)
				return 1600;
			if (var1 == Item.bucketLava.itemID)
				return 20000;
			if (var1 == Block.sapling.blockID)
				return 100;
			if (var1 == Item.blazeRod.itemID)
				return 2400;
			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	public void clearBuffer()
	{
		for (int i = 3; i < 7; i++)
			inv[i] = null;
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return furnaceCookTime * par1 / 200;
	}

	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = 200;
		}

		return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	public void cookIngredient()
	{
		if (this.canCook())
		{
			ItemStack smeltResult = FurnaceRecipes.smelting().getSmeltingResult(this.inv[0]);
			boolean shouldSmelt = false;

			if (smeltResult != null)
				shouldSmelt = true;

			for (int i = 3; i < 7; i++)
			{
				if (this.inv[i] != null)
				{
					continue;
				}
				else if (this.inv[i] == null)
				{
					if (shouldSmelt)
					{
						this.inv[i] = smeltResult.copy();
					}
					else
					{
						this.inv[i] = this.inv[0].copy();
						this.inv[i].stackSize = 1;
					}
					break;
				}
			}

			--this.inv[0].stackSize;

			if (this.inv[0].stackSize <= 0)
			{
				this.inv[0] = null;
			}
			updateResult();
		}
	}

	public void updateResult()
	{
		if (inv[3] != null)
		{
			Object[] tempIs = new Object[] { inv[3], inv[4], inv[5], inv[6] };
			if (WokRecipes.isRecipe(tempIs))
			{
				this.inv[7] = WokRecipes.getRecipe(tempIs).getResultStack();
			}
			else
			{
				this.inv[7] = createRandomResult(tempIs);
			}
		}
		else
		{
			this.inv[7] = null;
		}
	}

	private ItemStack createRandomResult(Object[] tempIs)
	{
		return new ItemStack(Block.dirt, 1);
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */
	private boolean canCook()
	{
		if (this.inv[0] == null)
		{
			return false;
		}
		else
		{
			if (inv[6] == null)
			{
				for (int i = 3; i < 7; i++)
				{
					if (inv[i] != null)
					{
						continue;
					}
					else if (inv[i] == null)
					{
						return true;
					}
				}
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize <= amt)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "Wok";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length)
			{
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, Player player, ByteArrayDataInput data, int fireLevel)
	{
		try
		{
			this.fireLevel = fireLevel;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
