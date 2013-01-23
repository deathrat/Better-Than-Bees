package deathrat.mods.btbees.api;

/*
 * Defines a tile entity that uses or provides power.
 */
public interface IMachine
{

	/**
	 *
	 * @return Whether or not this tile entity outputs energy.
	 */
	public boolean hasOutput();

	/**
	 *
	 * @return Whether or not this tile entity receives energy.
	 */
	public boolean hasInput();

	/**
	 *
	 * @return The amount of energy this tile entity has.
	 */
	public int getPower();

	/**
	 *
	 * @return The maximum amount of energy the tile entity can hold.
	 */
	public int getMaxPower();

	/**
	 * Set the amount of energy the tile entity has.
	 * @param power The amount of energy to set.
	 */
	public void setPower(int power);

	/**
	 *
	 * @return The ID of the block.
	 */
	public int getMachineId();

	/**
	 *
	 * @return Whether or not the tile entity is currently receiving power.
	 */
	public boolean isReceivingPower();

	/**
	 *
	 * @return Whether or not the tile entity is currently outputting power.
	 */
	public boolean isOutputtingPower();
}
