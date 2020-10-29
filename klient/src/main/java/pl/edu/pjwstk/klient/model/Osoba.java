package pl.edu.pjwstk.klient.model;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Osoba {
    private final Stacje cel;
    private final String imie;
    private final String nazwisko;

    public Osoba() {
        Faker faker = new Faker(new Locale("pl"));
        this.imie = faker.name().firstName();
        this.nazwisko = faker.name().lastName();
        this.cel = Stacje.values()[ThreadLocalRandom.current().nextInt(0, 14 + 1)];
    }

    public Stacje getCel() {
        return cel;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
