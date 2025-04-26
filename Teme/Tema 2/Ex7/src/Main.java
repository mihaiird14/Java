import java.util.*;
class Produs {
    private String cod;
    private String nume;
    private double pret;

    public Produs(String cod, String nume, double pret) {
        this.cod = cod;
        this.nume = nume;
        this.pret = pret;
    }

    public String getCod() {
        return cod;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs produs)) return false;
        return Objects.equals(cod, produs.cod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
    @Override
    public String toString() {
        return "Produs{" +
                "cod='" + cod + '\'' +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Produs> produseSet = new HashSet<>();

        Produs p1 = new Produs("1", "Laptop", 3000.0);
        Produs p2 = new Produs("2", "Telefon", 7200.0);
        Produs p3 = new Produs("3", "PS5", 4500.0);
        Produs p4 = new Produs("1", "Laptop Office", 2500.0);
        produseSet.add(p1);
        produseSet.add(p2);
        produseSet.add(p3);
        produseSet.add(p4);
        Map<Produs, Integer> stoc = new HashMap<>();
        stoc.put(p1, 10);
        stoc.put(p2, 5);
        stoc.put(p3, 15);
        stoc.put(p4, 25);
        produseSet.forEach(System.out::println);
        System.out.println("\nStocuri:");
        for (Map.Entry<Produs, Integer> x : stoc.entrySet()) {
            System.out.println(x.getKey() + " - Stoc: " + x.getValue());
        }
    }
}