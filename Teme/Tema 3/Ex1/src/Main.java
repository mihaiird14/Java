import java.io.*;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EroareNegativ extends Exception {
    public EroareNegativ(String message) {
        super(message);
    }
}

class Produs implements Serializable {
    private String nume;
    private double pret;
    private int stoc;

    public Produs(String nume, double pret, int stoc) throws EroareNegativ {
        if (pret < 0) {
            throw new EroareNegativ("Pretul nu pot fi negativ.");
        }
        if(stoc<0){
            throw new EroareNegativ("Stocul nu poate fi negativ.");
        }
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
    }

    public String getNume() {
        return nume;
    }
    public double getPret() {
        return pret;
    }
    public int getStoc() {
        return stoc;
    }
    @Override
    public String toString() {
        return String.format("Produsul '%s' are pretul %.2f, iar nr de bucati in stoc este %d}", nume, pret, stoc);
    }
}
class Citire {
    public static List<Produs> citesteProduse() {
        List<Produs> produse = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("produse.dat"))) {
            while (true) {
                try {
                    Produs p = (Produs) in.readObject();
                    produse.add(p);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produse;
    }
}
class Afisare {
    public static void AfisareProduse(List<Produs> produse) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("produse.dat"))) {
            produse.forEach(prod -> {
                try {
                    out.writeObject(prod);
                } catch (IOException e) {
                    logError(e.getMessage());
                }
            });
        } catch (IOException e) {
            logError(e.getMessage());
        }
    }

    private static void logError(String mesaj) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("erori.log", true))) {
            pw.println(mesaj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Gestionare {
    public static void Gestionare(List<Produs> produse) {

        List<Produs> epuizate = produse.stream()
                .filter(p -> p.getStoc() == 0)
                .collect(Collectors.toList());

        try (FileWriter writer = new FileWriter("epuizate.txt")) {
            epuizate.forEach(p -> {
                try {
                    writer.write(p.toString() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        UnaryOperator<Produs> reduce = p -> {
            try {
                return new Produs(p.getNume(), p.getPret(), (int)(p.getStoc() * 0.9));
            } catch (EroareNegativ e) {
                e.printStackTrace();
                return p;
            }
        };

        List<Produs> produseReduse = produse.stream()
                .map(reduce)
                .collect(Collectors.toList());

        produseReduse.stream()
                .max(Comparator.comparingDouble(Produs::getPret))
                .ifPresent(System.out::println);
    }
}
public class Main {
    public static void main(String[] args) {
        try {
            List<Produs> produse = Stream.of(
                   // new Produs("Test",-2,-3),
                    new Produs("Lapte", 7.5, 10),
                    new Produs("Paine", 3.0, 0),
                    new Produs("Carne", 12.0, 30),
                    new Produs("Zahar", 5.5, 0),
                    new Produs("Faina", 4.0, 25),
                    new Produs("Apa", 2.5, 50),
                    new Produs("Cafea", 30.0, 15),
                    new Produs("Ulei", 15.0, 8),
                    new Produs("Sapun", 6.0, 0),
                    new Produs("Detergent", 25.0, 12)
            ).collect(Collectors.toList());

            Afisare.AfisareProduse(produse);

            List<Produs> produseCitite = Citire.citesteProduse();

            Gestionare.Gestionare(produseCitite);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}