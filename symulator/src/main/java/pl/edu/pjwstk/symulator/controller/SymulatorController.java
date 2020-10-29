package pl.edu.pjwstk.symulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.symulator.model.Pociag;
import pl.edu.pjwstk.symulator.model.Stacje;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class SymulatorController {

    private List<Pociag> pociagi = new ArrayList<>();


    public SymulatorController(){

        int iloscPociagow;
        int iloscPrzedzialow;
        int iloscOsob;

        try {
            FileInputStream fstream = new FileInputStream("config.cfg");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            iloscPociagow = Integer.parseInt(br.readLine());
            iloscPrzedzialow = Integer.parseInt(br.readLine());
            iloscOsob = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            iloscPociagow = 2;
            iloscPrzedzialow = 4;
            iloscOsob = 8;
        }

        for(int i = 0; i < iloscPociagow; i++) {
            pociagi.add(new Pociag(iloscOsob, iloscPrzedzialow));
        }
    }

    @GetMapping("/doprzodu")
    public String doPrzodu() {
        for (Pociag pociag : pociagi) {
            if(!pociag.postoj) {
                int nowiPasazerowie = ThreadLocalRandom.current().nextInt(2, 8 + 1);
                pociag.nastepnaStacja();
                if(pociag.getStacja() == Stacje.GDANSK || pociag.getStacja() == Stacje.GDYNIA) {
                    pociag.postoj = true;
                    if(pociag.getStacja() == Stacje.GDYNIA) pociag.wraca = true;
                    else pociag.wraca = false;
                }
                pociag.wysiadajacy();
                pociag.wsiadajacy(nowiPasazerowie);
            } else {
                if(pociag.czasPostoju == 1) {
                    pociag.czasPostoju = 0;
                    pociag.postoj = false;
                } else pociag.czasPostoju++;
            }
        }
        return "Symulacja poszła o krok do przodu.";
    }

    @GetMapping("/pociagi")
    public List<Integer> getPociagi() {
        List<Integer> listaId = new ArrayList<>();
        for (Pociag pociag : pociagi) {
            listaId.add(pociag.getId());
        }
        return listaId;
    }
    @GetMapping("/getpociaginfo")
    public List<Pociag> getPociagInfo() {
        return pociagi;
    }
}
