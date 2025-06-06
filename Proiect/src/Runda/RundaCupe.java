package Runda;
import Tabla.*;
import Player.*;
import Carte.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class RundaCupe extends Runda{

    public RundaCupe(){
        super("Cupe");
    }

    @Override
    public void startRunda(List<Player> jucatori,List<Carte>pachetCarti,List<Integer>scoruriJoc) {
        getNume();
        AmestecareCarti(pachetCarti);
        int nr=0;
        for(Player p:jucatori){
            cartiRunda.put(p.getUsername(),new ArrayList<Carte>());
            for(int i=0;i<13;i++){
                cartiRunda.get(p.getUsername()).add(pachetCarti.get(nr));
                nr++;
            }
        }
        Random rand=new Random();
        int jucatorStart=rand.nextInt(4);

        for(int i=0;i<13;i++){
            int cnt=0;
            Tabla tabla = new Tabla();
            AtomicInteger valoareMaxim=new AtomicInteger();
            AtomicInteger playerIaMana=new AtomicInteger();
            AtomicBoolean primul=new AtomicBoolean(true);
            StringBuilder semnStart=new StringBuilder();
            int nrIR=0;
            while(cnt<4) {
                cnt++;

                Player p = jucatori.get(jucatorStart);
                System.out.println("Este randul jucatorului: " + p.getUsername());
                StringBuilder semnJucat=new StringBuilder();
                StringBuilder valJucata=new StringBuilder();
                if (p instanceof PlayerHuman) {
                    p.alegeCarte(primul, cartiRunda.get(p.getUsername()), semnStart, tabla,valoareMaxim,playerIaMana,semnJucat,valJucata);
                    tabla.afisTabla();
                } else {
                    p.alegeCarte(primul, cartiRunda.get(p.getUsername()), semnStart, tabla,valoareMaxim,playerIaMana,semnJucat,valJucata);
                    tabla.afisTabla();
                }
                if(semnJucat.toString().equals("I.ROSIE")){
                    nrIR+=1;
                }
                try {
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                jucatorStart++;
                if(jucatorStart==4)
                    jucatorStart=0;

            }
            jucatorStart=playerIaMana.get()-1;

            switch(jucatorStart){
                case 0:
                    scoruriJoc.set(0,scoruriJoc.get(0)-(nrIR*20));
                    break;
                case 1:
                    scoruriJoc.set(1,scoruriJoc.get(1)-(nrIR*20));
                    break;
                case 2:
                    scoruriJoc.set(2,scoruriJoc.get(2)-(nrIR*20));
                    break;
                case 3:
                    scoruriJoc.set(3,scoruriJoc.get(3)-(nrIR*20));
                    break;
            }
            try {
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            for(int j=0;j<10;j++){
                System.out.println();
            }
        }


    }
}
