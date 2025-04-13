package Optiuni;
import Player.*;
import ScoreBoard.*;
import Joc.*;
import java.util.Scanner;

public class Optiuni{
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
            System.out.print(e);
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
        System.out.println("2. CUPE = fiecare cupa luata -20puncte/cupa");
        System.out.println("3. DAME = fiecare dama luata -25puncte/dama");
        System.out.println("4. REGELE DE CUPA = -150puncte");
        System.out.println("5. TOTALE = cuprinde toate jocurile in care poti acumula punctaj negativ");
        System.out.println("6. WHIST = fiecare mana luata +10puncte/mana");
        System.out.println("La finalul jocului: ");
        System.out.println("        -- Locul 1 primeste 50 puncte (la scorul sau de jucator)");
        System.out.println("        -- Locul 2 primeste 10 puncte (la scorul sau de jucator)");
        System.out.println("        -- Locul 3 primeste 0 puncte (la scorul sau de jucator)");
        System.out.println("        -- Locul 4 este penalizat cu -20 puncte (la scorul sau de jucator) daca are scorul general >=500");
        System.out.println("                                   cu -10 puncte daca are scorul sub 500 puncte si mai mare decat 0");
        System.out.println("\n\n---OPTIUNI---");
        optiuni.afisOptiuni(player);
    }
}
