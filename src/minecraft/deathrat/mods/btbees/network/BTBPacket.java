package deathrat.mods.btbees.network;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.Player;

public abstract class BTBPacket
{
	public abstract void handle(ByteArrayDataInput byteDataInput, Player player);

	public abstract byte getPacketType();
}
