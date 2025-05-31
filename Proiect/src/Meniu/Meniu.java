package Meniu;

import Optiuni.*;
import Player.*;
import java.sql.*;
import java.util.Scanner;
import DB.*;
import Log.*;
public class Meniu {
    private static Meniu meniu;

    private Meniu() {}

    public static Meniu getMeniu() {
        if (meniu == null) {
            meniu = new Meniu();
        }
        return meniu;
    }

    private static void Conectare(Scanner scanner, String parolaBD, String username) {
        System.out.println("Bine ai revenit!");
        System.out.print("Parola: ");
        String parola = scanner.nextLine();
        if (parola.equals(parolaBD)) {
            System.out.println("Conectat!");
            Log.getInstance().logAction("PLAYER     READ     " + username);
            Optiuni opt = Optiuni.getOptiuni();
            PlayerHuman player = new PlayerHuman(username, parola);
            opt.afisOptiuni(player);
        } else {
            System.out.println("Parola gresita!");
        }
    }

    private void Inregistrare(Scanner scanner, String username) {
        System.out.println("Inregistrare cont nou!");
        System.out.print("Introduceti o parola: ");
        String parola = scanner.nextLine();
        while (parola.length() < 5) {
            System.out.println("Parola trebuie sa aiba cel putin 5 caractere!");
            System.out.print("Introduceti o parola: ");
            parola = scanner.nextLine();
        }

        try (Connection conn = DB.getInstance()) {
            String insertSQL = "INSERT INTO jucatori (username, parola, scor) VALUES (?, ?, 500)";
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setString(1, username);
            stmt.setString(2, parola);
            stmt.executeUpdate();

            Log.getInstance().logAction("PLAYER     INSERT     " + username);

            PlayerHuman player = new PlayerHuman(username, parola);
            player.InregistrarePlayer();

        } catch (SQLException e) {
            System.out.println("Eroare la inregistrare: " + e.getMessage());
        }
    }

    public void start() {
        boolean gasit = false;
        String username;
        String parolaBD = "";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        username = scanner.nextLine();

        try (Connection conn = DB.getInstance()) {
            String query = "SELECT parola FROM jucatori WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                gasit = true;
                parolaBD = rs.getString("parola");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea cu baza de date: " + e.getMessage());
        }

        if (gasit) {
            Conectare(scanner, parolaBD, username);
        } else {
            Inregistrare(scanner, username);
        }
    }
    public void stergeCont(String username) {
        try (Connection conn = DB.getInstance()) {
            String deleteOptiuniSQL = "DELETE FROM optiuni WHERE username = ?";
            try (PreparedStatement stmtOpt = conn.prepareStatement(deleteOptiuniSQL)) {
                stmtOpt.setString(1, username);
                int optRows = stmtOpt.executeUpdate();
                if (optRows > 0) {
                    Log.getInstance().logAction("OPTIUNI     DELETE    " + username);
                }
            }
            String deleteSQL = "DELETE FROM jucatori WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
                stmt.setString(1, username);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Contul a fost sters.");
                    Log.getInstance().logAction("PLAYER     DELETE     " + username);
                }
            }

        } catch (SQLException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }


}
