package ScoreBoard;


import Optiuni.*;
import Player.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard{
    private static ScoreBoard scoreBoard;
    private List<PlayerHuman> jucatori;
    private ScoreBoard(){
        jucatori=new ArrayList<>();
    };
    public static ScoreBoard getScoreBoard(){
        if(scoreBoard==null){
            scoreBoard=new ScoreBoard();
        }
        return scoreBoard;
    }
    public void Clasament(PlayerHuman player) {
        List<PlayerHuman> jucatori = new ArrayList<>();
        try {
            Path path = Paths.get("useri.txt");
            List<String> conturi = Files.readAllLines(path);
            for(String i : conturi){
                String[] valori = i.split(" ");
                jucatori.add(new PlayerHuman(valori[0], valori[1], Integer.parseInt(valori[2])));
            }
            Collections.sort(jucatori); //
            System.out.println("\n\n\n------------CLASAMENT----------------\n");
            int nr=0;
            for(PlayerHuman i : jucatori){
                System.out.println(i);
                nr++;
                if(nr==25){
                    break;
                }
            }
            System.out.println("\n\n---OPTIUNI---");
            Optiuni optiuni=Optiuni.getOptiuni();
            optiuni.afisOptiuni(player);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
