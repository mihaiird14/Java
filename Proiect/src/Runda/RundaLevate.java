package Runda;

import Player.Player;
import Carte.*;
import java.util.*;

public class RundaLevate extends Runda{

    public RundaLevate(){
        super("Levate");
    }

    @Override
    public void startRunda(Set<Player> jucatori,List<Carte>pachetCarti) {
        AmestecareCarti(pachetCarti);
        int nr=0;
        for(Player p:jucatori){
            cartiRunda.put(p.getUsername(),new ArrayList<Carte>());
            for(int i=0;i<13;i++){
                cartiRunda.get(p.getUsername()).add(pachetCarti.get(nr));
                nr++;
            }
        }
    }
}
