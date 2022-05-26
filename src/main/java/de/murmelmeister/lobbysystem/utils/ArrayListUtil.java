package de.murmelmeister.lobbysystem.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ArrayListUtil {
    private ArrayList<UUID> buildmode;
    private HashMap<UUID, Float> hue;

    public ArrayListUtil() {
        setBuildmode(new ArrayList<>());
        setHue(new HashMap<>());
    }

    public ArrayList<UUID> getBuildmode() {
        return buildmode;
    }

    public void setBuildmode(ArrayList<UUID> buildmode) {
        this.buildmode = buildmode;
    }

    public HashMap<UUID, Float> getHue() {
        return hue;
    }

    public void setHue(HashMap<UUID, Float> hue) {
        this.hue = hue;
    }
}
