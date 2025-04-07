package Meniu;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import Optiuni.*;
import Player.*;
public class Meniu{
    private static Meniu meniu;
    private Meniu(){}
    public static Meniu getMeniu(){
        if(meniu==null){
            meniu=new Meniu();
        }
        return meniu;
    }
    private static void Conectare(Scanner scanner, String dateUser, String username){
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