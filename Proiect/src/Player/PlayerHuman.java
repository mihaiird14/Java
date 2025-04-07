package Player;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PlayerHuman extends Player implements Comparable<PlayerHuman>{
    private String parola;
    private int highscore;
    public PlayerHuman(String username,String parola){
        super(username);
        this.parola=parola;
        this.highscore=0;
    }
    public PlayerHuman(String username,String parola,int highscore){
        super(username);
        this.parola=parola;
        this.highscore=highscore;
    }
    public String getParola(){
        return parola;
    }
    public int getHighscore(){
        return highscore;
    }
    public void InregistrarePlayer(){
        try {
            Files.write(
                    Paths.get("useri.txt"),
                    List.of(username + " " + parola + " 0\n"),
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE
            );
        }catch(Exception e){
            System.out.println("Eroare!");
        }
    }
    @Override
    public int compareTo(PlayerHuman player2){
        int sc=Integer.compare(player2.highscore,this.highscore);
        if(sc!=0){
            return sc;
        }
        else{
            return this.username.compareToIgnoreCase(player2.username);
        }
    }
    @Override
    public String toString(){
        return username + ": " + highscore+" puncte";
    }
}