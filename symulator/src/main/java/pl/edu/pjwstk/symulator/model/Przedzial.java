package pl.edu.pjwstk.symulator.model;

import java.util.ArrayList;
import java.util.List;

public class Przedzial {

    private int id;
    private int maxOsob;
    public List<Osoba> osoby = new ArrayList<>();

    public Przedzial() {

    }

    public Przedzial(int id, int maxOsob) {
        this.id = id;
        this.maxOsob = maxOsob;
    }

    public int getId() {
        return id;
    }

    public int getMaxOsob() {
        return maxOsob;
    }
}

