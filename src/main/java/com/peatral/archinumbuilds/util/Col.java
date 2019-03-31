package com.peatral.archinumbuilds.util;

import java.awt.*;

public class Col {
    public int r, g, b;

    public Col(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Col fromHex(String hex) {
        return fromColor(Color.decode(hex));
    }

    public static Col fromHexInDec(int dec) {
        String hex = "#"+Integer.toHexString(dec);
        return fromHex(hex);
    }

    public static Col fromColor(Color col) {
        return new Col(col.getRed(), col.getGreen(), col.getBlue());
    }

    public static Col getAverageOf(Col[] cols) {
        int r = 0;
        int g = 0;
        int b = 0;

        for (int i = 0; i < cols.length; i++) {
            r += cols[i].r;
            g += cols[i].g;
            b += cols[i].b;
        }
        return new Col(
                r / cols.length,
                g / cols.length,
                b / cols.length
        );
    }

    public Col getAverageTo(Col c2) {
        return new Col(
                (this.r + c2.r) / 2,
                (this.g + c2.g) / 2,
                (this.b + c2.b) / 2
        );
    }

    public String toHex() {
        return "#" + Integer.toHexString(this.r) + Integer.toHexString(this.g) + Integer.toHexString(this.b);
    }

    public int toIntFromHex() {
        return Integer.parseInt(this.toHex().replace("#",""), 16);
    }

    public static int toIntFromHex(String hex) {
        return Integer.parseInt(hex.replace("#",""), 16);
    }
}
