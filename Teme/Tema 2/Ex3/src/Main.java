import java.time.LocalDate;
import java.time.format.DateTimeParseException;
class RezervareException extends Exception {
    public RezervareException(String s) {
        super(s);
    }
}

class LocIndisponibilException extends RezervareException {
    public LocIndisponibilException(String s) {
        super(s);
    }
}

class DateInvalideException extends RezervareException {
    public DateInvalideException(String s) {
        super(s);
    }
}
class Rezervari {

    public void rezervaLoc(String data, int loc) throws RezervareException {

        try {
            LocalDate dataRezervare = LocalDate.parse(data);
            if (dataRezervare.isBefore(LocalDate.now())) {
                throw new DateInvalideException("Data este invalida!");
            }
        } catch (DateTimeParseException e) {
            throw new DateInvalideException("Formatul este incorect!");
        }

        if (loc <= 0 || loc > 100) {
            throw new LocIndisponibilException("Locul nu este disponibil!");
        }

        System.out.println("Rezervare reușită pentru data " + data + ", locul " + loc);
    }
}
public class Main {
    public static void main(String[] args) {
        Rezervari sistem = new Rezervari();

        try {
            sistem.rezervaLoc("2025-04-25", 50);
        } catch (LocIndisponibilException e) {
            System.out.println("Eroare: Loc indisponibil - " + e.getMessage());
        } catch (DateInvalideException e) {
            System.out.println("Eroare: Dată invalidă - " + e.getMessage());
        } catch (RezervareException e) {
            System.out.println("Eroare de rezervare: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Eroare generală: " + e.getMessage());
        }
    }
}
