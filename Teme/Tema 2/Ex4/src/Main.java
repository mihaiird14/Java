class SistemExistent {
    public void afiseazaXML(String xml) {
        System.out.println("Date în format XML:");
        System.out.println(xml);
    }
}
class SistemNou {
    public String genereazaJSON() {
        return "{ \"Titlu\": \"Carte1\", \"Autor\": Autor1 }";
    }
}
class AdaptorJsonToXml {
    private SistemExistent sistemExistent;

    public AdaptorJsonToXml(SistemExistent sistemExistent) {
        this.sistemExistent = sistemExistent;
    }

    public void trimiteDate(String json) {
        String xml = transformaJsonInXml(json);
        sistemExistent.afiseazaXML(xml);
    }

    private String transformaJsonInXml(String json) {
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .trim();
        String[] perechi = json.split(",");

        StringBuilder xml = new StringBuilder();
        xml.append("<root>\n");
        for (String pereche : perechi) {
            String[] cheieValoare = pereche.split(":");
            String titlu = cheieValoare[0].trim();
            String autor = cheieValoare[1].trim();
            xml.append("  <").append(titlu).append(">").append(autor)
                    .append("</").append(titlu).append(">\n");
        }
        xml.append("</root>");
        return xml.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        SistemNou sistemNou = new SistemNou();
        SistemExistent sistemExistent = new SistemExistent();
        AdaptorJsonToXml adaptor = new AdaptorJsonToXml(sistemExistent);

        String json = sistemNou.genereazaJSON();
        adaptor.trimiteDate(json);
        //Este util deoarece nu este necesar sa modificam codul sursă existent al
        // sistemului vechi.
    }
}
