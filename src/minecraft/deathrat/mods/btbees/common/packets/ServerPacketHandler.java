package deathrat.mods.btbees.common.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import deathrat.mods.btbees.common.blocks.TileEntityRicePlant;

public class ServerPacketHandler implements IPacketHandler
{
	public static final String CHANNEL = "btbees";
	private static HashMap packethandlers = new HashMap();

	public static void registerPacketHandler(BTBPacket packet)
	{
		if(packethandlers.get(Byte.valueOf(packet.getPacketType())) != null)
		{
			throw new RuntimeException("Multiple id registrations for packet type on BTBees channel");
		}
		packethandlers.put(Byte.valueOf(packet.getPacketType()), packet);
	}

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


	public static void sendRiceUpdate(TileEntityRicePlant tileEntity, int metaData)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try
		{
			data.writeInt(tileEntity.xCoord);
			data.writeInt(tileEntity.yCoord);
			data.writeInt(tileEntity.zCoord);
			data.writeInt(metaData);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "btbees";
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		packet.isChunkDataPacket = true;

		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if(side == Side.SERVER)
			PacketDispatcher.sendPacketToAllPlayers(packet);

		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BTBees] sendRiceUpdate called");
	}

}
