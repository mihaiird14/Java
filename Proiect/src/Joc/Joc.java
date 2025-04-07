package Joc;
import Player.*;
import Carte.*;
import Runda.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Joc{
    private Set<Player> jucatori=new HashSet<>();
    private List<Integer> scoruriJoc = new ArrayList<Integer>();
    private List<Carte> pachetCarti=new ArrayList<Carte>();
    private List<Runda> runde;
    public Joc(){
        for(int i=0;i<4;i++){
            scoruriJoc.add(0);
        }
    }
    public void setScoruriJoc(int a,int b,int c,int d){
        scoruriJoc.set(0, scoruriJoc.get(0) + a);
        scoruriJoc.set(1, scoruriJoc.get(1) + b);
        scoruriJoc.set(2, scoruriJoc.get(2) + c);
        scoruriJoc.set(3, scoruriJoc.get(3) + d);
    }
    private void setPachetCarti(){
        pachetCarti.add(new Carte("INIMA NEAGRA","AS"));
        pachetCarti.add(new Carte("INIMA NEAGRA","2"));
        pachetCarti.add(new Carte("INIMA NEAGRA","3"));
        pachetCarti.add(new Carte("INIMA NEAGRA","4"));
        pachetCarti.add(new Carte("INIMA NEAGRA","5"));
        pachetCarti.add(new Carte("INIMA NEAGRA","6"));
        pachetCarti.add(new Carte("INIMA NEAGRA","7"));
        pachetCarti.add(new Carte("INIMA NEAGRA","8"));
        pachetCarti.add(new Carte("INIMA NEAGRA","9"));
        pachetCarti.add(new Carte("INIMA NEAGRA","10"));
        pachetCarti.add(new Carte("INIMA NEAGRA","J"));
        pachetCarti.add(new Carte("INIMA NEAGRA","Q"));
        pachetCarti.add(new Carte("INIMA NEAGRA","K"));

        pachetCarti.add(new Carte("INIMA ROSIE","AS"));
        pachetCarti.add(new Carte("INIMA ROSIE","2"));
        pachetCarti.add(new Carte("INIMA ROSIE","3"));
        pachetCarti.add(new Carte("INIMA ROSIE","4"));
        pachetCarti.add(new Carte("INIMA ROSIE","5"));
        pachetCarti.add(new Carte("INIMA ROSIE","6"));
        pachetCarti.add(new Carte("INIMA ROSIE","7"));
        pachetCarti.add(new Carte("INIMA ROSIE","8"));
        pachetCarti.add(new Carte("INIMA ROSIE","9"));
        pachetCarti.add(new Carte("INIMA ROSIE","10"));
        pachetCarti.add(new Carte("INIMA ROSIE","J"));
        pachetCarti.add(new Carte("INIMA ROSIE","Q"));
        pachetCarti.add(new Carte("INIMA ROSIE","K"));

        pachetCarti.add(new Carte("TREFLA","AS"));
        pachetCarti.add(new Carte("TREFLA","2"));
        pachetCarti.add(new Carte("TREFLA","3"));
        pachetCarti.add(new Carte("TREFLA","4"));
        pachetCarti.add(new Carte("TREFLA","5"));
        pachetCarti.add(new Carte("TREFLA","6"));
        pachetCarti.add(new Carte("TREFLA","7"));
        pachetCarti.add(new Carte("TREFLA","8"));
        pachetCarti.add(new Carte("TREFLA","9"));
        pachetCarti.add(new Carte("TREFLA","10"));
        pachetCarti.add(new Carte("TREFLA","J"));
        pachetCarti.add(new Carte("TREFLA","Q"));
        pachetCarti.add(new Carte("TREFLA","K"));

        pachetCarti.add(new Carte("CAROU","AS"));
        pachetCarti.add(new Carte("CAROU","2"));
        pachetCarti.add(new Carte("CAROU","3"));
        pachetCarti.add(new Carte("CAROU","4"));
        pachetCarti.add(new Carte("CAROU","5"));
        pachetCarti.add(new Carte("CAROU","6"));
        pachetCarti.add(new Carte("CAROU","7"));
        pachetCarti.add(new Carte("CAROU","8"));
        pachetCarti.add(new Carte("CAROU","9"));
        pachetCarti.add(new Carte("CAROU","10"));
        pachetCarti.add(new Carte("CAROU","J"));
        pachetCarti.add(new Carte("CAROU","Q"));
        pachetCarti.add(new Carte("CAROU","K"));
        
    }

    public void startJoc(PlayerHuman player){
        jucatori.add(new Player("Bot1"));
        jucatori.add(new Player("Bot2"));
        jucatori.add(new Player("Bot3"));
        jucatori.add(player);
        setPachetCarti();
        runde=List.of(new RundaLevate());
        for(int i=0;i<1;i++){
            runde.get(0).startRunda(jucatori,pachetCarti);
        }
    }
}