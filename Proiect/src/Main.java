import java.nio.file.Files;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.util.*;

class Joc{
    private Set<Player> jucatori=new HashSet<>();
    private List<Integer>scoruriJoc;
    public Joc(){
        for(int i=0;i<4;i++){
            scoruriJoc.add(0);
        }
    }
    public void startJoc(PlayerHuman player){
        jucatori.add(new Player("Bot1"));
        jucatori.add(new Player("Bot2"));
        jucatori.add(new Player("Bot3"));
        jucatori.add(player);

    }
}
class Carte{
    private String semn;
    private String valoare;
    public Carte(String semn,String valoare){
        this.semn=semn;
        this.valoare=valoare;
    }
}
class Player{
    protected String username;
    public Player(String username){
        this.username=username;
    }
}
class PlayerHuman extends Player implements Comparable<PlayerHuman>{
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
    public String getUsername(){
        return username;
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
class ScoreBoard{
    private static ScoreBoard scoreBoard;
    private List<PlayerHuman>jucatori;
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
class Optiuni{
    private static Optiuni optiuni;
    private Optiuni(){}
    public static Optiuni getOptiuni(){
        if(optiuni==null){
            optiuni=new Optiuni();
        }
        return optiuni;
    }
    public void afisOptiuni(PlayerHuman player){
        System.out.println("1. Start Joc");
        System.out.println("2. Regulament");
        System.out.println("3. Score Board");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Introduceti optiunea:");
        try{
            int opt=scanner.nextInt();
            while(opt<1 || opt>3){
                opt=scanner.nextInt();
            }
            switch(opt){
                case 1:
                    Joc joc=new Joc();
                    joc.startJoc(player);
                    break;
                case 2:
                    this.Regulament(player);
                    break;
                case 3:
                    ScoreBoard scoreBoard=ScoreBoard.getScoreBoard();
                    scoreBoard.Clasament(player);
                    break;
            }
        }catch(Exception e){
            System.out.print("Eroare- optiunea trebuie sa fie un numar intre 1 si 3\n");
            this.afisOptiuni(player);
        }

    }
    public void Regulament(PlayerHuman player){
        System.out.print("\n\n\n\nREGULAMENT\n\n");
        System.out.println("Fiecare jucator pune o carte jos");
        System.out.println("Simbolul cartii puse de primul jucator, la fiecare mana, este simbolul pe care ceilalti jucatori trebuie sa-l joace!");
        System.out.println("Un jucator poate sa puna o carte cu alt simbol, doar daca nu are simbolul respectiv, insa cartea nu va fi socotita la punctaj");
        System.out.println("Jucatorul care are cartea cea mai mare (cu simbolul pus de primul jucator) ia mana");
        System.out.println("In fiecare runda, scorul difera, astfel: ");
        System.out.println("1. LEVATE = fiecare mana luata -10puncte/mana");
        System.out.println("2. CARO = fiecare caro luat -20puncte/caro");
        System.out.println("3. DAME = fiecare dama luata -25puncte/dama");
        System.out.println("4. REGELE DE CUPA = -150puncte");
        System.out.println("5. TOTALE = cuprinde toate jocurile in care poti acumula punctaj negativ");
        System.out.println("6. RENTZ = primul jucato alege cartea de baza (exemplu: AS). Se fac tean-uri pe simboluri");
        System.out.println("       =Fiecare jucator poate sa puna urmatoarea carte mai mare/mica dintr-un teanc sau o alta carte de baza");
        System.out.println("       =Locul 1: +800puncte, Locul 2: +400puncte, Locul 3: -100puncte, Locul 4: -200puncte");
        System.out.println("\n\n---OPTIUNI---");
        optiuni.afisOptiuni(player);
    }
}

class Meniu{
    private static Meniu meniu;
    private Meniu(){}
    public static Meniu getMeniu(){
        if(meniu==null){
            meniu=new Meniu();
        }
        return meniu;
    }
    private static void Conectare(Scanner scanner,String dateUser,String username){
        System.out.println("Bine ai revenit!");
        System.out.print("Parola: ");
        String parola=scanner.nextLine();
        if(parola.equals(dateUser)){
            System.out.println("Conectat!");
            Optiuni opt=Optiuni.getOptiuni();
            PlayerHuman player=new PlayerHuman(username,dateUser);
            opt.afisOptiuni(player);
        }
        else{
            System.out.println("Parola gresita!");
        }
    }
    private void Inregistrare(Scanner scanner,String username){
        System.out.println("Inregistrare cont nou!");
        System.out.print("Introduceti o parola:");
        String parola=scanner.nextLine();
        while(parola.length()<5){
            System.out.println("Parola trebuie sa aiba cel putin 5 caractere!");
            System.out.print("Introduceti o parola:");
            parola=scanner.nextLine();
        }
        PlayerHuman player=new PlayerHuman(username,parola);
        player.InregistrarePlayer();
    }
    public void start(){
        boolean gasit=false;
        String username;
        String dateUser="";
        System.out.print("Username: ");
        Scanner scanner=new Scanner(System.in);
        username=scanner.nextLine();
        try{
            List<String> conturi = Files.readAllLines(Paths.get("useri.txt"));

            for(String i:conturi){
                String[] elemente=i.split(" ");
                if(username.equals(elemente[0])){
                    gasit=true;
                    dateUser=elemente[1];
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Eroare!!");

        }
        if(gasit==true){
            Conectare(scanner,dateUser,username);

        }
        else{
            Inregistrare(scanner,username);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Meniu start=Meniu.getMeniu();
        start.start();
    }
}