import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Client {
    String nume;
    int varsta;
    double sumaCont;
    Optional<String> tipClient;

    public Client(String nume, int varsta, double sumaCont, Optional<String> tipClient) {
        this.nume = nume;
        this.varsta = varsta;
        this.sumaCont = sumaCont;
        this.tipClient = tipClient;
    }

    public String getTipClient() {
        return tipClient.orElse("Necunoscut");
    }
}

public class Main {
    public static void main(String[] args) {
        List<Client> clienti = Arrays.asList(
                new Client("Pers1", 30, 1500, Optional.of("VIP")),
                new Client("Pers2", 22, 800, Optional.of("Standard")),
                new Client("Pers3", 45, 2500, Optional.of("VIP")),
                new Client("Pers4", 19, 500, Optional.of("Nou")),
                new Client("Pers5", 35, 2000, Optional.empty()),
                new Client("Pers6", 28, 1800, Optional.of("Standard")),
                new Client("Pers7", 50, 3000, Optional.of("VIP")),
                new Client("Pers8", 23, 700, Optional.of("Nou")),
                new Client("Pers9", 27, 1200, Optional.of("Standard")),
                new Client("Pers10", 40, 2200, Optional.empty()),
                new Client("Pers11", 21, 600, Optional.of("Nou")),
                new Client("Pers12", 55, 3500, Optional.of("VIP"))
        );

        double medie = clienti.stream()
                .mapToDouble(c -> c.sumaCont)
                .average()
                .orElse(0);

        Predicate<Client> vipPesteMedie = c ->
                c.getTipClient().equals("VIP") && c.sumaCont > medie;

        List<Client> v = clienti.stream()
                .filter(vipPesteMedie)
                .collect(Collectors.toList());

        System.out.println("Clienti VIP:");
        v.forEach(c -> System.out.println(c.nume));

        Function<Client, String> formatClient = c -> c.nume + " - " + c.varsta + " ani";

        List<String> formatare = clienti.stream()
                .map(formatClient)
                .collect(Collectors.toList());

        System.out.println("\nFormatted clients:");
        formatare.forEach(System.out::println);

        BinaryOperator<Double> sumOperator = Double::sum;

        double totalSuma = clienti.stream()
                .map(c -> c.sumaCont)
                .reduce(0.0, sumOperator);

        System.out.println("\nSuma totala: " + totalSuma);

        Map<String, Long> grupare = clienti.stream()
                .collect(Collectors.groupingBy(Client::getTipClient, Collectors.counting()));

        System.out.println("\nGrupare:");
        grupare.forEach((tip, count) -> System.out.println(tip + ": " + count));

        String sub25 = clienti.stream()
                .filter(c -> c.varsta < 25)
                .map(c -> c.nume)
                .collect(Collectors.joining(", "));

        System.out.println("\nSub 25: " + sub25);
    }
}
