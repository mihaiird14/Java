package Joc;
import ClasamentJoc.ClasamentJoc;
import Log.Log;
import Player.*;
import Carte.*;
import Runda.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Joc{
    private List<Player> jucatori=new ArrayList<>();
    private List<Integer> scoruriJoc = new ArrayList<Integer>();
    private List<Carte> pachetCarti=new ArrayList<Carte>();
    private List<Runda> runde;
    public Joc(){
        for(int i=0;i<4;i++){
            scoruriJoc.add(0);
        }
    }
    public void setScoruriJoc(int a,int b,int c,int d){
        scoruriJoc.set(0, scoruriJoc.get(0) + a);
        scoruriJoc.set(1, scoruriJoc.get(1) + b);
        scoruriJoc.set(2, scoruriJoc.get(2) + c);
        scoruriJoc.set(3, scoruriJoc.get(3) + d);
    }
    private void setPachetCarti(){
        pachetCarti.add(new Carte("I.NEAG","AS",14));
        pachetCarti.add(new Carte("I.NEAG","2",2));
        pachetCarti.add(new Carte("I.NEAG","3",3));
        pachetCarti.add(new Carte("I.NEAG","4",4));
        pachetCarti.add(new Carte("I.NEAG","5",5));
        pachetCarti.add(new Carte("I.NEAG","6",6));
        pachetCarti.add(new Carte("I.NEAG","7",8));
        pachetCarti.add(new Carte("I.NEAG","8",9));
        pachetCarti.add(new Carte("I.NEAG","9",9));
        pachetCarti.add(new Carte("I.NEAG","10",10));
        pachetCarti.add(new Carte("I.NEAG","J",11));
        pachetCarti.add(new Carte("I.NEAG","Q",12));
        pachetCarti.add(new Carte("I.NEAG","K",13));

        pachetCarti.add(new Carte("I.ROSIE","AS",14));
        pachetCarti.add(new Carte("I.ROSIE","2",2));
        pachetCarti.add(new Carte("I.ROSIE","3",3));
        pachetCarti.add(new Carte("I.ROSIE","4",4));
        pachetCarti.add(new Carte("I.ROSIE","5",5));
        pachetCarti.add(new Carte("I.ROSIE","6",6));
        pachetCarti.add(new Carte("I.ROSIE","7",7));
        pachetCarti.add(new Carte("I.ROSIE","8",8));
        pachetCarti.add(new Carte("I.ROSIE","9",9));
        pachetCarti.add(new Carte("I.ROSIE","10",10));
        pachetCarti.add(new Carte("I.ROSIE","J",11));
        pachetCarti.add(new Carte("I.ROSIE","Q",12));
        pachetCarti.add(new Carte("I.ROSIE","K",13));

        pachetCarti.add(new Carte("TREFLA","AS",14));
        pachetCarti.add(new Carte("TREFLA","2",2));
        pachetCarti.add(new Carte("TREFLA","3",3));
        pachetCarti.add(new Carte("TREFLA","4",4));
        pachetCarti.add(new Carte("TREFLA","5",5));
        pachetCarti.add(new Carte("TREFLA","6",6));
        pachetCarti.add(new Carte("TREFLA","7",7));
        pachetCarti.add(new Carte("TREFLA","8",8));
        pachetCarti.add(new Carte("TREFLA","9",9));
        pachetCarti.add(new Carte("TREFLA","10",10));
        pachetCarti.add(new Carte("TREFLA","J",11));
        pachetCarti.add(new Carte("TREFLA","Q",12));
        pachetCarti.add(new Carte("TREFLA","K",13));

        pachetCarti.add(new Carte("CARO","AS",14));
        pachetCarti.add(new Carte("CARO","2",2));
        pachetCarti.add(new Carte("CARO","3",3));
        pachetCarti.add(new Carte("CARO","4",4));
        pachetCarti.add(new Carte("CARO","5",5));
        pachetCarti.add(new Carte("CARO","6",6));
        pachetCarti.add(new Carte("CARO","7",7));
        pachetCarti.add(new Carte("CARO","8",8));
        pachetCarti.add(new Carte("CARO","9",9));
        pachetCarti.add(new Carte("CARO","10",10));
        pachetCarti.add(new Carte("CARO","J",11));
        pachetCarti.add(new Carte("CARO","Q",12));
        pachetCarti.add(new Carte("CARO","K",13));

    }

    public void startJoc(PlayerHuman player) {
        jucatori.add(new Player("Bot1"));
        jucatori.add(new Player("Bot2"));
        jucatori.add(new Player("Bot3"));
        jucatori.add(player);
        setPachetCarti();

        runde = List.of(
                new RundaLevate(), new RundaCupe(), new RundaDame(),
                new RundaPopa(), new RundaWhist(), new RundaTotale()
        );

        try (Connection conn = DB.getInstance()) {
            String insertSQL = "INSERT INTO runda (tiprunda) VALUES (?)";
            String updateSQL = "UPDATE runda SET tiprunda = ?";
            String readSQL = "SELECT tiprunda FROM runda";
            String deleteSQL = "DELETE FROM runda";

            PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
            PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
            PreparedStatement readStmt = conn.prepareStatement(readSQL);
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);

            for (int i = 0; i < runde.size(); i++) {
                String numeRunda = runde.get(i).getClass().getSimpleName();

                if (i == 0) {
                    insertStmt.setString(1, numeRunda);
                    insertStmt.executeUpdate();
                    Log.getInstance().logAction("RUNDA      INSERT:     tiprunda = " + numeRunda);
                } else {
                    updateStmt.setString(1, numeRunda);
                    updateStmt.executeUpdate();
                    Log.getInstance().logAction("RUNDA      UPDATE:     tiprunda = " + numeRunda);
                }

                ResultSet rs = readStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("\n=== RUNDA CURENTĂ: " + rs.getString("tiprunda") + " ===\n");
                    Log.getInstance().logAction("RUNDA      READ:       tiprunda = " + rs.getString("tiprunda"));
                }

                runde.get(i).startRunda(jucatori, pachetCarti, scoruriJoc);

                if (i < 5) {
                    System.out.println("Scoruri după runda " + (i + 1) + ": ");
                    for (int j = 0; j < 4; j++) {
                        System.out.println(jucatori.get(j).getUsername() + " " + scoruriJoc.get(j) + " puncte");
                    }
                } else {
                    System.out.println("Clasament final");
                    List<ClasamentJoc> cls = new ArrayList<>();
                    for (int j = 0; j < 4; j++) {
                        cls.add(new ClasamentJoc(jucatori.get(j), scoruriJoc.get(j)));
                    }

                    Collections.sort(cls, Comparator.comparingInt(ClasamentJoc::getScor).reversed());

                    int loc = 1;
                    for (ClasamentJoc c : cls) {
                        if (c.getPlayer() instanceof PlayerHuman) {
                            System.out.print("Locul " + loc + ": " + c.getPlayer().getUsername() + " ");
                            PlayerHuman ph = (PlayerHuman) c.getPlayer();
                            int scorVechi = ph.getHighscore();
                            switch (loc) {
                                case 1: ph.setHighscore(50); break;
                                case 2: ph.setHighscore(10); break;
                                case 3: ph.setHighscore(0); break;
                                case 4:
                                    if (ph.getHighscore() >= 500) {
                                        ph.setHighscore(-20);
                                    } else if (ph.getHighscore() - 10 >= 0) {
                                        ph.setHighscore(-10);
                                    }
                                    break;
                            }
                            System.out.println(scorVechi + " -> " + ph.getHighscore());

                            try (PreparedStatement stmt = conn.prepareStatement(
                                    "UPDATE jucatori SET scor = ? WHERE username = ?")) {
                                stmt.setInt(1, ph.getHighscore());
                                stmt.setString(2, ph.getUsername());
                                stmt.executeUpdate();
                                Log.getInstance().logAction("PLAYER     UPDATE");
                            } catch (SQLException e) {
                                System.out.println("Eroare la update scor: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Locul " + loc + ": " + c.getPlayer().getUsername());
                        }
                        loc++;
                    }

                    deleteStmt.executeUpdate();
                    Log.getInstance().logAction("RUNDA      DELETE");
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroare în gestionarea rundelor: " + e.getMessage());
        }
    }

}