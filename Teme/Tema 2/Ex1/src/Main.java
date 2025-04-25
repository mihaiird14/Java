interface PoateSterge{}
interface PoateEdita{}
interface PoateVizualiza{}
abstract class Utilizator{
    String nume;
    Utilizator(String s){
        nume=s;
    }
}
class Administrator extends Utilizator implements PoateEdita,PoateSterge,PoateVizualiza{
    Administrator(String s){
        super(s);
    }
}
class Editor extends Utilizator implements PoateEdita,PoateVizualiza{
    Editor(String s){
        super(s);
    }
}
class Vizitator extends Utilizator implements PoateVizualiza{
    Vizitator(String s){
        super(s);
    }
}
class ActiuneService {

    public void permisiuni(Utilizator utilizator) {
        System.out.println("Permisiuni pentru: " + utilizator.nume);

        if (utilizator instanceof PoateEdita) {
            System.out.println(" Acest utilizator poate edita");
        }
        if (utilizator instanceof PoateSterge) {
            System.out.println(" Acest utilizator poate sterge");
        }
        if (utilizator instanceof PoateVizualiza) {
            System.out.println(" Acest utilizator poate vizualiza");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Utilizator admin = new Administrator("Admin");
        Utilizator editor = new Editor("Editor");
        Utilizator vizitator = new Vizitator("Vizitator");
        ActiuneService service = new ActiuneService();
        service.permisiuni(admin);
        service.permisiuni(editor);
        service.permisiuni(vizitator);
    }
}