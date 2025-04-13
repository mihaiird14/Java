package Runda;
import Joc.*;
import Carte.*;
import Player.Player;

import java.util.Collections;
import java.util.*;

interface RundaJoc{
    void startRunda(List<Player> jucatori,List<Carte>pachetCarti,List<Integer>scoruriJoc);
}
public abstract class Runda implements RundaJoc{
    protected String nume;
    protected Map<String,List<Carte>> cartiRunda = new HashMap<>();
    public Runda(String nume){
        this.nume=nume;
    }
    public void AmestecareCarti(List<Carte>pachetCarti){
        Collections.shuffle(pachetCarti);
    }
    public abstract void startRunda(List<Player> jucatori,List<Carte>pachetCarti,List<Integer>scoruriJoc);
    public void getNume(){
        System.out.println("Acum se joaca: "+nume);
    }
}
