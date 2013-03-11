package deathrat.mods.btbees.util;

import java.awt.Color;

public class ColorUtil
{

	public static Color colorIntToColor(int colorint)
	{
		int blue = colorint & 255;
		int green = (colorint >> 8) & 255;
		int red = (colorint >> 16) & 255;

		return new Color(red, green, blue);
	}

	public static float[] colorRGBToFloat(int r, int g, int b, int a)
	{
		float[] array = { (float) (r / 256), (float) (g / 256), (float) (b / 256), (float) (a / 256) };
		return array;
	}

}
