import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Comanda implements Serializable {
    private int id;
    private String numeClient;
    private double valoare;
    private boolean finalizata;

    public Comanda(int id, String numeClient, double valoare, boolean finalizata) {
        this.id = id;
        this.numeClient = numeClient;
        this.valoare = valoare;
        this.finalizata = finalizata;
    }

    public int getId() {
        return id;
    }
    public String getNumeClient() {
        return numeClient;
    }
    public double getValoare() {
        return valoare;
    }
    public boolean isFinalizata() {
        return finalizata;
    }

    @Override
    public String toString() {
        return id + " | " + numeClient + " | " + valoare + " | Finalizata: " + (finalizata ? "DA" : "NU");
    }
}
class Citire {
    public static List<Comanda> CitireComenzi() {
        List<Comanda> comenzi = new ArrayList<>();
        try (RandomAccessFile x = new RandomAccessFile("comenzi.dat", "r")) {
            while (x.getFilePointer() < x.length()) {
                int id = x.readInt();
                String numeClient = x.readUTF();
                double valoare = x.readDouble();
                boolean finalizata = x.readBoolean();
                comenzi.add(new Comanda(id, numeClient, valoare, finalizata));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comenzi;
    }
}

class AfisareComenzi {
    public static void scrieComenzi(List<Comanda> comenzi) {
        try (RandomAccessFile x = new RandomAccessFile("comenzi.dat", "rw")) {
            x.setLength(0);
            for (Comanda c : comenzi) {
                x.writeInt(c.getId());
                x.writeUTF(c.getNumeClient());
                x.writeDouble(c.getValoare());
                x.writeBoolean(c.isFinalizata());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Update {
    public static void updateComanzi(double limita) {
        try (RandomAccessFile x = new RandomAccessFile("comenzi.dat", "rw")) {
            while (x.getFilePointer() < x.length()) {
                long pos = x.getFilePointer();

                int id = x.readInt();
                String numeClient = x.readUTF();
                double valoare = x.readDouble();
                boolean finalizata = x.readBoolean();

                if (valoare > limita) {
                    x.seek(pos + 4 + numeClient.getBytes().length + 2 + 8);
                    x.writeBoolean(true);
                    x.seek(x.getFilePointer());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
class GestionareComenzi {
    public static void proceseazaComenzi(List<Comanda> comenzi) {
        List<Comanda> finalizate = comenzi.stream()
                .filter(Comanda::isFinalizata)
                .collect(Collectors.toList());

        double suma = finalizate.stream()
                .mapToDouble(Comanda::getValoare)
                .sum();
        Map<String, List<Comanda>> grupate = finalizate.stream()
                .collect(Collectors.groupingBy(Comanda::getNumeClient));

        System.out.println("Comenzi finalizate:");
        finalizate.forEach(System.out::println);

        System.out.println("\nSuma comenzilor finalizate: " + suma);

        System.out.println("\nComenzi finalizate grupate pe client:");
        grupate.forEach((client, lista) -> {
            System.out.println(client + ":");
            lista.forEach(System.out::println);
        });
    }
}
public class Main {
    public static void main(String[] args) {
        try {
            List<Comanda> comenzi = IntStream.range(1, 16)
                    .mapToObj(i -> new Comanda(i, "Client " + (i % 5), 1000 + Math.random() * 7000, false))
                    .collect(Collectors.toList());


            AfisareComenzi.scrieComenzi(comenzi);
            Update.updateComanzi(5000);
            List<Comanda> comenziActualizate = Citire.CitireComenzi();
            GestionareComenzi.proceseazaComenzi(comenziActualizate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}