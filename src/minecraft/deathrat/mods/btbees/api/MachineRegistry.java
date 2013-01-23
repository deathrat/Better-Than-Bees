package deathrat.mods.btbees.api;

import java.util.HashMap;
import java.util.Map;

public class MachineRegistry
{
	public static Map<Integer, IMachine> machines = new HashMap<Integer, IMachine>();

	public MachineRegistry()
	{
	}

	public static void registerMachine(IMachine machine)
	{
		machines.put(machine.getMachineId(), machine);
	}
}