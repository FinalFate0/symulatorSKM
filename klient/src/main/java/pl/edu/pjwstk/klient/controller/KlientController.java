package pl.edu.pjwstk.klient.controller;

import org.json.simple.JSONValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.edu.pjwstk.klient.model.Osoba;
import pl.edu.pjwstk.klient.model.Pociag;
import pl.edu.pjwstk.klient.model.Przedzial;

import java.util.*;

@RestController
public class KlientController {

    private final String URI_pociagi = "http://symulator:8080/pociagi";
    private final String URI_getpociaginfo = "http://symulator:8080/getpociaginfo";
    private final String URI_doprzodu = "http://symulator:8080/doprzodu";

    @GetMapping("/getpociagi")
    public List<Integer> getPociagi() {
        RestTemplate template = new RestTemplate();

        Integer[] pociagiArray = template.getForObject(URI_pociagi, Integer[].class);
        assert pociagiArray != null;
        return Arrays.asList(pociagiArray);

    }

    @GetMapping("/getpociagi/{id}")
    public String getPociagInfo(@PathVariable int id) {
        RestTemplate template = new RestTemplate();

        Pociag[] pociagi = template.getForObject(URI_getpociaginfo, Pociag[].class);

        assert pociagi != null;
        for(Pociag pociag : pociagi) {
            if(pociag.getId() == id) {
                Map json = new LinkedHashMap();
                json.put("id", id);
                json.put("stacja", pociag.getNazwaStacji());
                json.put("ilość osób", pociag.getIloscOsob());
                List<Integer> listaIdPrzedzialow = new ArrayList<>();
                for (Przedzial przedzial : pociag.getPrzedzialy()) {
                    listaIdPrzedzialow.add(przedzial.getId());
                }
                return JSONValue.toJSONString(json) + " lista przedziałów: " + listaIdPrzedzialow;
            }
        }
        return null;
    }

    @GetMapping("/getpociagi/{idPociag}/{idPrzedzial}")
    public String getPrzedzialInfo(@PathVariable int idPociag, @PathVariable int idPrzedzial) {
        RestTemplate template = new RestTemplate();

        Pociag[] pociagi = template.getForObject(URI_getpociaginfo, Pociag[].class);

        assert pociagi != null;
        for(Pociag pociag : pociagi) {
            if(pociag.getId() == idPociag) {
                for(Przedzial przedzial : pociag.getPrzedzialy()) {
                    if (przedzial.getId() == idPrzedzial) {
                        Map json = new LinkedHashMap();
                        json.put("id", przedzial.getId());
                        json.put("ilość osób", przedzial.osoby.size());
                        json.put("max osób", przedzial.getMaxOsob());
                        List<Integer> listaIdOsob = new ArrayList<>();
                        for (Osoba osoba : przedzial.osoby) {
                            listaIdOsob.add(przedzial.osoby.indexOf(osoba)+1);
                        }
                        return JSONValue.toJSONString(json) + " lista osób: " + listaIdOsob;
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/getpociagi/{idPociag}/{idPrzedzial}/{idOsoba}")
    public String getOsobaInfo(@PathVariable int idPociag, @PathVariable int idPrzedzial, @PathVariable int idOsoba) {
        RestTemplate template = new RestTemplate();

        Pociag[] pociagi = template.getForObject(URI_getpociaginfo, Pociag[].class);

        assert pociagi != null;
        for(Pociag pociag : pociagi) {
            if(pociag.getId() == idPociag) {
                for(Przedzial przedzial : pociag.getPrzedzialy()) {
                    if (przedzial.getId() == idPrzedzial) {
                        for(Osoba osoba : przedzial.osoby) {
                            if(przedzial.osoby.indexOf(osoba)+1 == idOsoba) {
                                LinkedHashMap json = new LinkedHashMap();
                                json.put("id osoby", przedzial.osoby.indexOf(osoba) + 1);
                                json.put("imie", osoba.getImie());
                                json.put("nazwisko", osoba.getNazwisko());
                                json.put("stacja docelowa", osoba.getCel().getNazwa());
                                return JSONValue.toJSONString(json);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/doprzodu")
    public String doPrzodu() {
        RestTemplate template = new RestTemplate();
        return template.getForObject(URI_doprzodu, String.class);
    }

}
