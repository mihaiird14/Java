package Runda;
import Joc.*;
import Carte.*;
import Player.Player;

import java.util.Collections;
import java.util.*;

interface RundaJoc{
    void startRunda(Set<Player> jucatori,List<Carte>pachetCarti);
}
public abstract class Runda implements RundaJoc{
    protected String nume;
    protected Map<String,List<Carte>> cartiRunda = new HashMap<>();
    public Runda(String nume){
        this.nume=nume;
    }
    public String numeRunda(){
        return nume;
    }
    public void AmestecareCarti(List<Carte>pachetCarti){
        Collections.shuffle(pachetCarti);
    }
    public abstract void startRunda(Set<Player> jucatori,List<Carte>pachetCarti);
}
