import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Persoana {
    String nume;
    int varsta;
    String oras;

    public Persoana(String nume, int varsta, String oras) {
        this.nume = nume;
        this.varsta = varsta;
        this.oras = oras;
    }

    @Override
    public String toString() {
        return nume + ";" + varsta + ";" + oras;
    }
}

public class Main {

    public static void main(String[] args) {
        adaugaInFisier();

        List<Persoana> persoane = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("date.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    persoane.add(new Persoana(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }

            List<Persoana> filtru = persoane.stream()
                    .filter(p -> p.varsta > 30 && p.oras.startsWith("B"))
                    .sorted(Comparator.comparing((Persoana p) -> p.nume)
                            .thenComparing(p -> p.varsta))
                    .collect(Collectors.toList());

            Map<String, List<Persoana>> grOras = persoane.stream()
                    .collect(Collectors.groupingBy(p -> p.oras));

            Map<String, Double> medie = persoane.stream()
                    .collect(Collectors.groupingBy(p -> p.oras,
                            Collectors.averagingInt(p -> p.varsta)));

            Optional<Persoana> varstaMaxima = persoane.stream()
                    .reduce((p1, p2) -> p1.varsta > p2.varsta ? p1 : p2);

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("rezultat.txt"))) {
                writer.write("Persoane filtrate și sortate:\n");
                for (Persoana p : filtru) {
                    writer.write(p.toString() + "\n");
                }

                writer.write("\nGrupare pe oraș:\n");
                for (Map.Entry<String, List<Persoana>> entry : grOras.entrySet()) {
                    writer.write(entry.getKey() + ":\n");
                    for (Persoana p : entry.getValue()) {
                        writer.write("  " + p.toString() + "\n");
                    }
                }

                writer.write("\nMedia de vârstă per oraș:\n");
                for (Map.Entry<String, Double> entry : medie.entrySet()) {
                    writer.write(entry.getKey() + " -> media: " + entry.getValue() + "\n");
                }

                if (varstaMaxima.isPresent()) {
                    writer.write("\nPersoana cu vârsta maximă:\n");
                    writer.write(varstaMaxima.get().toString() + "\n");
                }
            }

            System.out.println("Rezultatele au fost scrise în rezultat.txt");

        } catch (IOException e) {
            System.err.println("Eroare la citirea sau scrierea fișierului: " + e.getMessage());
        }
    }

    public static void adaugaInFisier() {
        Path path = Paths.get("date.txt");
        if (Files.exists(path)) {
            System.out.println("Fișierul există deja.");
            return;
        }

        List<String> sampleData = Arrays.asList(
                "Persoana 1;35;Bucuresti",
                "Persoana 2;28;Brasov",
                "Persoana 3;45;Baia Mare",
                "Persoana 4;22;Cluj",
                "Persoana 5;55;Bucuresti",
                "Persoana 6;19;Constanta",
                "Persoana 7;40;Botosani",
                "Persoana 8;31;Bacau",
                "Persoana 9;50;Bucuresti",
                "Persoana 10;27;Arad"
        );

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : sampleData) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Fisier creat!");
        } catch (IOException e) {
            System.err.println("Eroare creare fișier: " + e.getMessage());
        }
    }
}
