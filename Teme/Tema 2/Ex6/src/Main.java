interface Dispozitiv {
    void porneste();
    void opreste();

    default void status() {
        System.out.println("Status: " + verificaStatusIntern());
    }

    static void descriereGenerala() {
        System.out.println("Dispozitiv electric.");
    }

    private String verificaStatusIntern() {
        return "Funcționează!";
    }
}
interface Smart extends Dispozitiv {
    void actualizeazaSoftware();
}
interface Conectabil extends Dispozitiv {
    void conecteazaLaInternet();
}
class Telefon implements Smart, Conectabil {
    @Override
    public void porneste() {
        System.out.println("Telefonul este pornit.");
    }

    @Override
    public void opreste() {
        System.out.println("Telefonul este oprit.");
    }

    @Override
    public void actualizeazaSoftware() {
        System.out.println("Telefonul si-a actualizat software-ul.");
    }

    @Override
    public void conecteazaLaInternet() {
        System.out.println("Telefonul este conectat la internet.");
    }
}
class Smartwatch implements Smart {
    @Override
    public void porneste() {
        System.out.println("Smartwatch-ul este pornit.");
    }

    @Override
    public void opreste() {
        System.out.println("Smartwatch-ul este oprit.");
    }

    @Override
    public void actualizeazaSoftware() {
        System.out.println("Smartwatch-ul si-a actualizat software-ul.");
    }
}
class Televizor implements Conectabil {
    @Override
    public void porneste() {
        System.out.println("Televizorul este pornit.");
    }

    @Override
    public void opreste() {
        System.out.println("Televizorul este oprit.");
    }

    @Override
    public void conecteazaLaInternet() {
        System.out.println("Televizorul este conectat la internet.");
    }
}

public class Main {
    public static void main(String[] args) {
        Telefon telefon = new Telefon();
        Smartwatch smartwatch = new Smartwatch();
        Televizor televizor = new Televizor();

        telefon.porneste();
        telefon.actualizeazaSoftware();
        telefon.conecteazaLaInternet();
        telefon.status();
        System.out.println();

        smartwatch.porneste();
        smartwatch.actualizeazaSoftware();
        smartwatch.status();
        System.out.println();

        televizor.porneste();
        televizor.conecteazaLaInternet();
        televizor.status();
        System.out.println();

        Dispozitiv.descriereGenerala();
    }
}
