sealed abstract class MetodaPlata
        permits Card, Cash, TransferBancar {
}
final class Card extends MetodaPlata {
    private String cvv;
    private String dataExp;

    public Card(String cvv, String dataExp) {
        this.cvv = cvv;
        this.dataExp = dataExp;
    }

    public boolean valideaza() {
        System.out.println("Validare Card:");
        return cvv.length() == 3 && dataExp.matches("\\d{2}/\\d{2}");
    }
}

final class Cash extends MetodaPlata {
    public void proceseaza() {
        System.out.println("Tranzacția acceptata.");
    }
}

final class TransferBancar extends MetodaPlata {
    private String iban;

    public TransferBancar(String iban) {
        this.iban = iban;
    }

    public boolean valideaza() {
        System.out.println("Validare Transfer Bancar:");
        return iban.startsWith("RO") && iban.length() == 24;
    }
}
class ProcesatorPlata {
    public void proceseazaPlata(MetodaPlata metoda) {
        if (metoda instanceof Card card) {
            if (card.valideaza()) {
                System.out.println("Card valid. Plata acceptată.");
            } else {
                System.out.println("Card invalid. Plata respinsă.");
            }
        } else if (metoda instanceof Cash cash) {
            cash.proceseaza();
        } else if (metoda instanceof TransferBancar transfer) {
            if (transfer.valideaza()) {
                System.out.println("Transfer bancar valid. Plata acceptată.");
            } else {
                System.out.println("IBAN invalid. Plata respinsă.");
            }
        } else {
            System.out.println("Metodă de plată necunoscută.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProcesatorPlata procesator = new ProcesatorPlata();

        MetodaPlata card = new Card("111", "12/29");
        MetodaPlata cash = new Cash();
        MetodaPlata transfer = new TransferBancar("RO49XDAA1B32407593110000");

        procesator.proceseazaPlata(card);
        System.out.println();
        procesator.proceseazaPlata(cash);
        System.out.println();
        procesator.proceseazaPlata(transfer);
    }
}
