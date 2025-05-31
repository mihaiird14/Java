package ScoreBoard;

import Optiuni.Optiuni;
import Player.PlayerHuman;
import java.sql.*;
import java.util.ArrayList;
import Log.*;
import java.util.List;
import DB.*;
public class ScoreBoard {
    private static ScoreBoard scoreBoard;
    private List<PlayerHuman> jucatori;

    private ScoreBoard() {
        jucatori = new ArrayList<>();
    }

    public static ScoreBoard getScoreBoard() {
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void Clasament(PlayerHuman player) {
        try (Connection conn = DB.getInstance()) {

            try (PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM scoreboard")) {
                deleteStmt.executeUpdate();
                Log.getInstance().logAction("SCOREBOARD      DELETE");
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO scoreboard (username, scor) SELECT username, scor FROM jucatori")) {
                insertStmt.executeUpdate();
                Log.getInstance().logAction("SCOARBOARD     INSERT");
            }

            try (PreparedStatement selectStmt = conn.prepareStatement(
                    "SELECT username, scor FROM scoreboard ORDER BY scor DESC LIMIT 25");
                 ResultSet rs = selectStmt.executeQuery()) {
                Log.getInstance().logAction("SCOAREBOARD      READ");
                System.out.println("\n\n\n------------CLASAMENT----------------\n");
                int poz = 1;
                while (rs.next()) {
                    String username = rs.getString("username");
                    int scor = rs.getInt("scor");
                    System.out.println(poz + ". " + username + " - " + scor + " puncte");
                    poz++;
                }
            }

            System.out.println("\n\n---OPTIUNI---");
            Optiuni optiuni = Optiuni.getOptiuni();
            optiuni.afisOptiuni(player);

        } catch (SQLException e) {
            System.out.println("Eroare la generarea clasamentului: " + e.getMessage());
        }
    }
}
