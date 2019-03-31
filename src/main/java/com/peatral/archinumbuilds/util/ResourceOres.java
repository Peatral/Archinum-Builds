package com.peatral.archinumbuilds.util;

public enum ResourceOres
{
	ARCHINUM("Archinum"),
	KOROSIUM("Korosium"),
	CHROMIUM("Chromium");

	private String name;

	ResourceOres(String name)
	{
		this.name = name;
	}

	public static ResourceOres getFromName(String s)
	{
		for(ResourceOres r : values())
		{
			if(r.name.toLowerCase().equals(s.toLowerCase()))
			{
				return r;
			}
		}

		return null;
	}

	public String getName()
	{
		return name;
	}
}