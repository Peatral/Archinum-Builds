package com.peatral.archinumbuilds.util;

public enum Resource
{
	ARCHINUM("Archinum"),
	KOROSIUM("Korosium"),
	STAR("Star"),
	DRAGON("Dragon");//,
	//BOSS("Boss");

	private String name;

	private Resource(String s)
	{
		name = s;
	}

	public static Resource getFromName(String s)
	{
		for(Resource r : values())
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