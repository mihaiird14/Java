package Joc;
import ClasamentJoc.ClasamentJoc;
import Player.*;
import Carte.*;
import Runda.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Joc{
    private List<Player> jucatori=new ArrayList<>();
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
        pachetCarti.add(new Carte("I.NEAG","AS",14));
        pachetCarti.add(new Carte("I.NEAG","2",2));
        pachetCarti.add(new Carte("I.NEAG","3",3));
        pachetCarti.add(new Carte("I.NEAG","4",4));
        pachetCarti.add(new Carte("I.NEAG","5",5));
        pachetCarti.add(new Carte("I.NEAG","6",6));
        pachetCarti.add(new Carte("I.NEAG","7",8));
        pachetCarti.add(new Carte("I.NEAG","8",9));
        pachetCarti.add(new Carte("I.NEAG","9",9));
        pachetCarti.add(new Carte("I.NEAG","10",10));
        pachetCarti.add(new Carte("I.NEAG","J",11));
        pachetCarti.add(new Carte("I.NEAG","Q",12));
        pachetCarti.add(new Carte("I.NEAG","K",13));

        pachetCarti.add(new Carte("I.ROSIE","AS",14));
        pachetCarti.add(new Carte("I.ROSIE","2",2));
        pachetCarti.add(new Carte("I.ROSIE","3",3));
        pachetCarti.add(new Carte("I.ROSIE","4",4));
        pachetCarti.add(new Carte("I.ROSIE","5",5));
        pachetCarti.add(new Carte("I.ROSIE","6",6));
        pachetCarti.add(new Carte("I.ROSIE","7",7));
        pachetCarti.add(new Carte("I.ROSIE","8",8));
        pachetCarti.add(new Carte("I.ROSIE","9",9));
        pachetCarti.add(new Carte("I.ROSIE","10",10));
        pachetCarti.add(new Carte("I.ROSIE","J",11));
        pachetCarti.add(new Carte("I.ROSIE","Q",12));
        pachetCarti.add(new Carte("I.ROSIE","K",13));

        pachetCarti.add(new Carte("TREFLA","AS",14));
        pachetCarti.add(new Carte("TREFLA","2",2));
        pachetCarti.add(new Carte("TREFLA","3",3));
        pachetCarti.add(new Carte("TREFLA","4",4));
        pachetCarti.add(new Carte("TREFLA","5",5));
        pachetCarti.add(new Carte("TREFLA","6",6));
        pachetCarti.add(new Carte("TREFLA","7",7));
        pachetCarti.add(new Carte("TREFLA","8",8));
        pachetCarti.add(new Carte("TREFLA","9",9));
        pachetCarti.add(new Carte("TREFLA","10",10));
        pachetCarti.add(new Carte("TREFLA","J",11));
        pachetCarti.add(new Carte("TREFLA","Q",12));
        pachetCarti.add(new Carte("TREFLA","K",13));

        pachetCarti.add(new Carte("CARO","AS",14));
        pachetCarti.add(new Carte("CARO","2",2));
        pachetCarti.add(new Carte("CARO","3",3));
        pachetCarti.add(new Carte("CARO","4",4));
        pachetCarti.add(new Carte("CARO","5",5));
        pachetCarti.add(new Carte("CARO","6",6));
        pachetCarti.add(new Carte("CARO","7",7));
        pachetCarti.add(new Carte("CARO","8",8));
        pachetCarti.add(new Carte("CARO","9",9));
        pachetCarti.add(new Carte("CARO","10",10));
        pachetCarti.add(new Carte("CARO","J",11));
        pachetCarti.add(new Carte("CARO","Q",12));
        pachetCarti.add(new Carte("CARO","K",13));
        
    }

    public void startJoc(PlayerHuman player){
        jucatori.add(new Player("Bot1"));
        jucatori.add(new Player("Bot2"));
        jucatori.add(new Player("Bot3"));
        jucatori.add(player);
        setPachetCarti();
        runde=List.of(new RundaLevate(),new RundaCupe(),new RundaDame(),new RundaPopa(),new RundaWhist(),new RundaTotale());
        for(int i=0;i<6;i++){
            runde.get(i).startRunda(jucatori,pachetCarti,scoruriJoc);
            if(i<5) {
                System.out.println("Scoruri dupa runda " + (i + 1) + ": ");
                System.out.println(jucatori.get(0).getUsername() + " " + scoruriJoc.get(0) + " puncte");
                System.out.println(jucatori.get(1).getUsername() + " " + scoruriJoc.get(1) + " puncte");
                System.out.println(jucatori.get(2).getUsername() + " " + scoruriJoc.get(2) + " puncte");
                System.out.println(jucatori.get(3).getUsername() + " " + scoruriJoc.get(3) + " puncte");
            }
            else{
                System.out.println("Clasament final ");
                List<ClasamentJoc>cls=new ArrayList<>();
                cls.add(new ClasamentJoc(jucatori.get(0),scoruriJoc.get(0)));
                cls.add(new ClasamentJoc(jucatori.get(1),scoruriJoc.get(1)));
                cls.add(new ClasamentJoc(jucatori.get(2),scoruriJoc.get(2)));
                cls.add(new ClasamentJoc(jucatori.get(3),scoruriJoc.get(3)));
                Collections.sort(cls, new Comparator<ClasamentJoc>() {
                    @Override
                    public int compare(ClasamentJoc o1, ClasamentJoc o2) {
                        return Integer.compare(o2.getScor(), o1.getScor());
                    }
                });
                int loc=1;
                for(ClasamentJoc c:cls){
                    if(c.getPlayer() instanceof PlayerHuman){
                        System.out.print("Locul "+loc+ ": "+ c.getPlayer().getUsername()+" ");
                        switch(loc){
                            case 1:
                                System.out.print(((PlayerHuman) c.getPlayer()).getHighscore()+" -> ");
                                ((PlayerHuman) c.getPlayer()).setHighscore(50);
                                System.out.println(((PlayerHuman) c.getPlayer()).getHighscore());
                                break;
                            case 2:
                                System.out.print(((PlayerHuman) c.getPlayer()).getHighscore()+" -> ");
                                ((PlayerHuman) c.getPlayer()).setHighscore(10);
                                System.out.println(((PlayerHuman) c.getPlayer()).getHighscore());
                                break;
                            case 3:
                                System.out.print(((PlayerHuman) c.getPlayer()).getHighscore()+" -> ");
                                ((PlayerHuman) c.getPlayer()).setHighscore(0);
                                System.out.println(((PlayerHuman) c.getPlayer()).getHighscore());
                                break;
                            case 4:
                                if(((PlayerHuman) c.getPlayer()).getHighscore()>=500){
                                    System.out.print(((PlayerHuman) c.getPlayer()).getHighscore()+" -> ");
                                    ((PlayerHuman) c.getPlayer()).setHighscore(-20);
                                    System.out.println(((PlayerHuman) c.getPlayer()).getHighscore());
                                }
                                else{
                                    if(((PlayerHuman) c.getPlayer()).getHighscore()-10>=0){
                                        System.out.print(((PlayerHuman) c.getPlayer()).getHighscore()+" -> ");
                                        ((PlayerHuman) c.getPlayer()).setHighscore(-10);
                                        System.out.println(((PlayerHuman) c.getPlayer()).getHighscore());
                                    }
                                }
                                break;
                        }
                        try{
                            List<String> conturi = Files.readAllLines(Paths.get("useri.txt"));
                            List<String> linii=new ArrayList<>();
                            for(String cont:conturi){
                                String[] elemente=cont.split(" ");
                                if(c.getPlayer().getUsername().equals(elemente[0])){
                                      linii.add(elemente[0]+" "+elemente[1]+" "+((PlayerHuman) c.getPlayer()).getHighscore());
                                }
                                else{
                                    linii.add(elemente[0]+" "+elemente[1]+" "+elemente[2]);
                                }
                            }
                            try{
                                Files.write(Paths.get("useri.txt"),linii);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        catch (Exception e){
                            System.out.println("Eroare!");
                        }
                    }
                    else{
                        System.out.println("Locul "+loc+ ": "+ c.getPlayer().getUsername());
                    }
                    loc++;
                }
            }
        }
    }
}