package com.peatral.archinumbuilds.util;

public enum ResinWoodTypes
{
    MYRRH("Myrrh"),
    OLIBANUM("Olibanum");

    private String name;

    ResinWoodTypes(String name)
    {
        this.name = name;
    }

    public static ResinWoodTypes getFromName(String s)
    {
        for(ResinWoodTypes r : values())
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