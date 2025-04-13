package ClasamentJoc;
import Player.*;
public class ClasamentJoc {
    private Player player;
    private int Scor;
    public ClasamentJoc(Player player,int Scor){
        this.player=player;
        this.Scor=Scor;
    }
    public Player getPlayer() {
        return player;
    }

    public int getScor() {
        return Scor;
    }
}
