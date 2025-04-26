import java.util.*;
abstract class OrganismViu {
    public abstract void respira();
    public abstract void seHraneste();
}
abstract class Animal extends OrganismViu {
    @Override
    public final void respira() {
        System.out.println("Animalul respiră aer.");
    }
    protected String tipHrana;
}
abstract class Mamifer extends Animal {
    protected boolean arePar() {
        return true;
    }
}
 final class Urs extends Mamifer {
    public Urs() {
        this.tipHrana = "fructe";
    }

    @Override
    public void seHraneste() {
        System.out.println("Ursul se hrănește cu " + tipHrana );
    }

    @Override
    protected boolean arePar() {
        return true;
    }
}
final class Delfin extends Mamifer {
    public Delfin() {
        this.tipHrana = "pește";
    }

    @Override
    public void seHraneste() {
        System.out.println("Delfinul se hrănește cu " + tipHrana);
    }

    @Override
    protected boolean arePar() {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        List<OrganismViu> v = new ArrayList<>();
        v.add(new Urs());
        v.add(new Delfin());

        for (OrganismViu o : v) {
            o.respira();
            o.seHraneste();

            if (o instanceof Mamifer mamifer) {
                System.out.println("Are păr: " + mamifer.arePar());
            }
            System.out.println();
        }
    }
}