package pl.edu.pjwstk.klient.model;

public enum Stacje {
    GDANSK("Gdańsk Główny"),
    STOCZNIA("Gdańsk Stocznia"),
    POLI("Gdańsk Politechnika"),
    WRZESZCZ( "Gdańsk Wrzeszcz"),
    ZASPA("Gdańsk Zaspa"),
    PRZYMORZE("Gdańsk Przymorze"),
    OLIWA("Gdańsk Oliwa"),
    ZABIANKA("Gdańsk Żabianka"),
    WYSCIGI("Sopot Wyścigi"),
    SOPOT("Sopot"),
    KAM_POT("Sopot Kamienny Potok"),
    ORLOWO("Gdynia Orłowo"),
    REDLOWO("Gdynia Redłowo"),
    WZGORZE_SW_MAKS("Gdynia Wzgórze Św. Maksymiliana"),
    GDYNIA("Gdynia Główna");

    private final String nazwa;

    Stacje(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}
