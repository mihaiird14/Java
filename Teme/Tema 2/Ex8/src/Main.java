import java.util.ArrayList;
import java.util.List;
enum NivelAcces {
    ADMIN(1, "Acces complet"),
    EDITOR(2, "Acces editare"),
    USER(3, "Acces utilizator"),
    GUEST(4, "Acces vizitator");
    private final int cod;
    private final String descriere;
    NivelAcces(int cod, String descriere) {
        this.cod = cod;
        this.descriere = descriere;
    }
    public int getCod() {
        return cod;
    }
    public String getDescriere() {
        return descriere;
    }
    public static NivelAcces dinCod(int cod) {
        for (NivelAcces n : NivelAcces.values()) {
            if (n.getCod() == cod) {
                return n;
            }
        }
        throw new IllegalArgumentException("Cod invalid: " + cod);
    }
}
class ContUtilizator {
    private String nume;
    private NivelAcces nivelAcces;

    public ContUtilizator(String s, NivelAcces n) {
        this.nume = s;
        this.nivelAcces = n;
    }

    public String getNume() {
        return nume;
    }

    public NivelAcces getNivelAcces() {
        return nivelAcces;
    }

    @Override
    public String toString() {
        return "ContUtilizator{" +
                "nume='" + nume + '\'' +
                ", nivelAcces=" + nivelAcces +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<ContUtilizator> conturi = new ArrayList<>();
        conturi.add(new ContUtilizator("User1", NivelAcces.ADMIN));
        conturi.add(new ContUtilizator("User2", NivelAcces.USER));
        conturi.add(new ContUtilizator("User3", NivelAcces.EDITOR));
        conturi.add(new ContUtilizator("User4", NivelAcces.GUEST));

        NivelAcces nm = NivelAcces.EDITOR;

        System.out.println("Conturi Admin sau Editor: ");
        conturi.stream()
                .filter(cont -> cont.getNivelAcces().ordinal() <= nm.ordinal())
                .forEach(System.out::println);
    }
}