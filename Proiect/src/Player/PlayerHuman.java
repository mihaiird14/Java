package Player;

import Carte.Carte;
import Tabla.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerHuman extends Player implements Comparable<PlayerHuman>{
    private String parola;
    private int highscore;
    public PlayerHuman(String username,String parola){
        super(username);
        this.parola=parola;
        this.highscore=500;
    }
    public PlayerHuman(String username,String parola,int highscore){
        super(username);
        this.parola=parola;
        this.highscore=highscore;
    }
    public String getParola(){
        return parola;
    }
    public void setHighscore(int x){
         highscore+=x;
    }
    public int getHighscore(){
        return highscore;
    }
    public void afisareCartiHumanPlayer(List<Carte>cartiJucator){
        String linie1="";
        for(int i=0;i<cartiJucator.size();i++){
            linie1+="-----------" + "   ";
        }
        System.out.println(linie1);
        linie1="";
        for(Carte c:cartiJucator){
            if(c.getSemn().equals("I.ROSIE")){
                linie1+="--"+c.getSemn()+"--";
            }
            else{
                if(c.getSemn().equals("CARO")){
                    linie1+="---"+c.getSemn()+"----";
                }
                else{
                    linie1+="---"+c.getSemn()+"--";
                }
            }
            linie1+="   ";
        }
        System.out.println(linie1);
        linie1="";
        for(Carte c:cartiJucator){
            if(!c.getValoare().equals("AS") && !c.getValoare().equals("10")) {
                linie1+="-----" + c.getValoare() + "-----";
            }
            else{
                linie1+="-----" + c.getValoare() + "----";
            }
            linie1+="   ";
        }
        System.out.println(linie1);
        linie1="";
        for(int i=0;i<cartiJucator.size();i++){
            linie1+="-----------" + "   ";
        }
        System.out.println(linie1);
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
    public void alegeCarte(AtomicBoolean primul, List<Carte> cartiJucator, StringBuilder semnStart, Tabla tabla, AtomicInteger valoareMaxim, AtomicInteger playerIaMana,StringBuilder semnJucat,StringBuilder valJucata) {
        if (primul.get()) {
            primul.set(false);
            Collections.sort(cartiJucator, new Comparator<Carte>() {
                @Override
                public int compare(Carte o1, Carte o2) {
                    int cmp=o1.getSemn().compareTo(o2.getSemn());
                    if(cmp==0){
                        return Integer.compare(o1.getValComp(),o2.getValComp());
                    }
                    return cmp;
                }
            });
            afisareCartiHumanPlayer(cartiJucator);
            int joacaCarte=0;
            Scanner scanner=new Scanner(System.in);
            while(joacaCarte<1 || joacaCarte>cartiJucator.size()){
                System.out.print("Alege cartea pe care vrei sa o joci (1-"+cartiJucator.size()+"): ");
                try{
                    joacaCarte=scanner.nextInt();
                }catch (Exception e){
                    System.out.println("Nu ai introdus bine!");
                }
            }
            valoareMaxim.set(cartiJucator.get(joacaCarte-1).getValComp());
            playerIaMana.set(4);
            semnStart.replace(0,semnStart.length(),cartiJucator.get(joacaCarte-1).getSemn());
            semnJucat.replace(0,semnJucat.length(),cartiJucator.get(joacaCarte-1).getSemn());
            valJucata.replace(0,semnJucat.length(),cartiJucator.get(joacaCarte-1).getValoare());
            String[] carte=cartiJucator.get(joacaCarte-1).desen();
            tabla.setTabla(4,carte);
            cartiJucator.remove(joacaCarte-1);


        } else {

            List<Carte> cartiPosibile = new ArrayList<>();
            for (Carte c : cartiJucator) {
                if (c.getSemn().contentEquals(semnStart)) {
                    cartiPosibile.add(c);
                }
            }
            Collections.sort(cartiPosibile, new Comparator<Carte>() {
                @Override
                public int compare(Carte o1, Carte o2) {
                    int cmp=o1.getSemn().compareTo(o2.getSemn());
                    if(cmp==0){
                        return Integer.compare(o1.getValComp(),o2.getValComp());
                    }
                    return cmp;
                }
            });
            if(cartiPosibile.size()>0) {
                afisareCartiHumanPlayer(cartiPosibile);
                int joacaCarte=0;
                Scanner scanner=new Scanner(System.in);
                while(joacaCarte<1 || joacaCarte>cartiPosibile.size()){
                    System.out.print("Alege cartea pe care vrei sa o joci (1-"+cartiPosibile.size()+"): ");
                    try{
                        joacaCarte=scanner.nextInt();
                    }catch (Exception e){
                        System.out.println("Nu ai introdus bine!");
                    }
                }
                if(cartiPosibile.get(joacaCarte-1).getValComp()>valoareMaxim.get()){
                    valoareMaxim.set(cartiPosibile.get(joacaCarte-1).getValComp());
                    playerIaMana.set(4);
                }
                semnJucat.replace(0,semnJucat.length(),cartiPosibile.get(joacaCarte-1).getSemn());
                valJucata.replace(0,semnJucat.length(),cartiPosibile.get(joacaCarte-1).getValoare());
                String[] carte=cartiPosibile.get(joacaCarte-1).desen();
                tabla.setTabla(4,carte);
                int ind = 0;
                for (Carte c : cartiJucator) {
                    if (c.getSemn().equals(cartiPosibile.get(joacaCarte-1).getSemn()) && c.getValoare().equals(cartiPosibile.get(joacaCarte-1).getValoare())) {
                        break;
                    } else {
                        ind++;
                    }
                }

                cartiJucator.remove(cartiJucator.get(ind));
            }
            else{
                Collections.sort(cartiJucator, new Comparator<Carte>() {
                    @Override
                    public int compare(Carte o1, Carte o2) {
                        int cmp=o1.getSemn().compareTo(o2.getSemn());
                        if(cmp==0){
                            return Integer.compare(o1.getValComp(),o2.getValComp());
                        }
                        return cmp;
                    }
                });
                afisareCartiHumanPlayer(cartiJucator);
                int joacaCarte=0;
                Scanner scanner=new Scanner(System.in);
                while(joacaCarte<1 || joacaCarte>cartiJucator.size()){
                    System.out.print("Alege cartea pe care vrei sa o joci (1-"+cartiJucator.size()+"): ");
                    try{
                        joacaCarte=scanner.nextInt();
                    }catch (Exception e){
                        System.out.println("Nu ai introdus bine!");
                    }
                }
                String[] carte=cartiJucator.get(joacaCarte-1).desen();
                tabla.setTabla(4,carte);
                semnJucat.replace(0,semnJucat.length(),cartiJucator.get(joacaCarte-1).getSemn());
                valJucata.replace(0,semnJucat.length(),cartiJucator.get(joacaCarte-1).getValoare());
                cartiJucator.remove(joacaCarte-1);
            }
        }
    }
}