package deathrat.mods.btbees.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.blocks.TileEntityRicePlant;

@SideOnly(Side.CLIENT)
public class ClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		try
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			int x = data.readInt();
			int y = data.readInt();
			int z = data.readInt();
			int meta = data.readInt();
			World world = ((EntityPlayer)player).worldObj;

			if(world != null)
			{
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

				if(tileEntity != null)
				{
					if(tileEntity instanceof TileEntityRicePlant)
					{
						((TileEntityRicePlant)tileEntity).handlePacketData(manager, packet, player, data, meta);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
