package de.murmelmeister.lobbysystem.utils;

import java.util.ArrayList;
import java.util.UUID;

public class ArrayListUtil {
    private ArrayList<UUID> buildmode;

    public ArrayListUtil() {
        setBuildmode(new ArrayList<>());
    }

    public ArrayList<UUID> getBuildmode() {
        return buildmode;
    }

    public void setBuildmode(ArrayList<UUID> buildmode) {
        this.buildmode = buildmode;
    }
}
