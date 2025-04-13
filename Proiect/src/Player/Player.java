package Player;
import Carte.*;
import Tabla.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Player{
    protected String username;
    public Player(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void alegeCarte(AtomicBoolean primul, List<Carte> cartiJucator, StringBuilder semnStart, Tabla tabla, AtomicInteger valoareMaxim,AtomicInteger playerIaMana,StringBuilder semnJucat,StringBuilder valJucata){
        if(primul.get()){
            primul.set(false);
            Random random=new Random();
            int randCarte=random.nextInt(cartiJucator.size());
            valoareMaxim.set(cartiJucator.get(randCarte).getValComp());
            semnStart.replace(0,semnStart.length(),cartiJucator.get(randCarte).getSemn());
            semnJucat.replace(0,semnJucat.length(),cartiJucator.get(randCarte).getSemn());
            valJucata.replace(0,semnJucat.length(),cartiJucator.get(randCarte).getValoare());
            String[] carte=cartiJucator.get(randCarte).desen();
            cartiJucator.remove(cartiJucator.get(randCarte));
            if(username.equals("Bot1")){
                tabla.setTabla(1,carte);
                playerIaMana.set(1);
            }
            else{
                if(username.equals("Bot2")){
                    tabla.setTabla(2,carte);
                    playerIaMana.set(2);
                }
                else{
                    if(username.equals("Bot3")){
                        tabla.setTabla(3,carte);
                        playerIaMana.set(3);
                    }
                    else{
                        tabla.setTabla(4,carte);
                    }
                }
            }
        }
        else{
            List<Carte>cartiPosibile=new ArrayList<>();
            for(Carte c:cartiJucator){
                if(c.getSemn().contentEquals(semnStart)){
                    cartiPosibile.add(c);
                }
            }
            if(cartiPosibile.size()>0) {
                Random random = new Random();
                int randCarte = random.nextInt(cartiPosibile.size());
                String[] carte = cartiPosibile.get(randCarte).desen();
                semnJucat.replace(0,semnJucat.length(),cartiPosibile.get(randCarte).getSemn());
                valJucata.replace(0,semnJucat.length(),cartiPosibile.get(randCarte).getValoare());
                if(cartiPosibile.get(randCarte).getValComp()>valoareMaxim.get()){
                    valoareMaxim.set(cartiPosibile.get(randCarte).getValComp());
                    if (username.equals("Bot1")){
                        playerIaMana.set(1);
                    }
                    else{
                        if (username.equals("Bot2")){
                            playerIaMana.set(2);
                        }
                        else{
                            playerIaMana.set(3);
                        }
                    }
                }
                if (username.equals("Bot1")) {
                    tabla.setTabla(1, carte);
                } else {
                    if (username.equals("Bot2")) {
                        tabla.setTabla(2, carte);
                    } else {
                        if (username.equals("Bot3")) {
                            tabla.setTabla(3, carte);
                        } else {
                            tabla.setTabla(4, carte);
                        }
                    }
                }
                int ind = 0;
                for (Carte c : cartiJucator) {
                    if (c.getSemn().equals(cartiPosibile.get(randCarte).getSemn()) && c.getValoare().equals(cartiPosibile.get(randCarte).getValoare())) {
                        break;
                    } else {
                        ind++;
                    }
                }

                    cartiJucator.remove(cartiJucator.get(ind));
            }
            else {
                Random random2 = new Random();
                int randCarte2 = random2.nextInt(cartiJucator.size());
                String[] carte = cartiJucator.get(randCarte2).desen();
                semnJucat.replace(0,semnJucat.length(),cartiJucator.get(randCarte2).getSemn());
                valJucata.replace(0,semnJucat.length(),cartiJucator.get(randCarte2).getValoare());
                if (username.equals("Bot1")) {
                    tabla.setTabla(1, carte);
                } else {
                    if (username.equals("Bot2")) {
                        tabla.setTabla(2, carte);
                    } else {
                        if (username.equals("Bot3")) {
                            tabla.setTabla(3, carte);
                        } else {
                            tabla.setTabla(4, carte);
                        }
                    }

                    cartiJucator.remove(cartiJucator.get(randCarte2));
                }
            }
        }
    }
}
