package pl.edu.pjwstk.klient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pociag {
    private int id;
    private Stacje stacja;
    private List<Przedzial> przedzialy = new ArrayList<>();
    public int czasPostoju = 0;
    public boolean postoj = false;
    public boolean wraca = false;

    public Pociag() {
    }

    public Pociag(int maxOsob, int iloscPrzedzialow) {

        this.id = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        this.stacja = Stacje.values()[ThreadLocalRandom.current().nextInt(0, 14 + 1)];
        for (int i=0;i<iloscPrzedzialow;i++) {
            this.przedzialy.add(new Przedzial(i+1, maxOsob));
        }
    }


    public int getId() {
        return id;
    }

    public Stacje getStacja() {
        return this.stacja;
    }

    public String getNazwaStacji() {
        return stacja.getNazwa();
    }

    public void nastepnaStacja() {
        int index = stacja.ordinal();
        int nastepnyIndex;
        Stacje[] listaStacji = Stacje.values();
        if(wraca) {
            nastepnyIndex = index - 1;
        } else {
            nastepnyIndex = index + 1;
        }
        nastepnyIndex %= listaStacji.length;
        this.stacja = listaStacji[nastepnyIndex];
    }

    public void wsiadajacy(int iloscPasazerow) {
        for(int i=0;i<iloscPasazerow;i++) {
            for(Przedzial przedzial : this.przedzialy) {
                if(przedzial.getMaxOsob() > przedzial.osoby.size()) {
                    przedzial.osoby.add(new Osoba());
                    break;
                }
            }
        }
    }

    public void wysiadajacy() {
        for(Przedzial przedzial : this.przedzialy) {
            przedzial.osoby.removeIf(osoba -> osoba.getCel() == this.stacja);
        }
    }

    public int getIloscOsob() {
        int iloscOsob = 0;
        for(Przedzial przedzial : this.przedzialy) {
            iloscOsob += przedzial.osoby.size();
        }
        return iloscOsob;
    }

    public List<Przedzial> getPrzedzialy() {
        return this.przedzialy;
    }
}
