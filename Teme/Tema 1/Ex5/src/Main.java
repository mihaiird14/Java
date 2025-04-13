import java.util.ArrayList;
class Carte implements Cloneable{
    private String titlu;
    private String autor;
    private int anAparitie;
    private ArrayList<String> citita;
    public Carte(String titlu,String autor,int anAparitie){
        this.titlu=titlu;
        this.autor=autor;
        this.anAparitie=anAparitie;
        this.citita = new ArrayList<>();
        citita.add("nu");
    }
    public Carte(Carte carte2){
        this.titlu=carte2.titlu;
        this.autor=carte2.autor;
        this.anAparitie=carte2.anAparitie;
        this.citita = new ArrayList<>();
        citita.add("nu");
    }
    public Carte deepCopy(){
        return new Carte(this);
    }
    public Carte ShallowCopy() throws CloneNotSupportedException{
        return (Carte) super.clone();
    }
    @Override
    public String toString(){
        return "Carte{titlu= " + titlu + ", Autor: " + autor + ", An Aparitie: " + anAparitie+"}";
    }

    public void detalii(){
        System.out.println("Titlu: "+titlu);
        System.out.println("Autor: "+autor);
        System.out.println("An Aparitie: "+anAparitie);
    }
    public void detalii(boolean TitluAutor){
        if(TitluAutor){
            System.out.println("Titlu: "+titlu);
            System.out.println("Autor: "+autor);
        }
        else{
            detalii();
        }
    }
    public void verificare(){
        citita.add(" da");
    }
    public void getCopy(){
        System.out.println(citita);
    }
}

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Carte carte1= new Carte("Aventura","Anonim",2000);
        carte1.detalii();
        carte1.detalii(true);
        Carte[] carti = new Carte[3];
        Carte[] carti2 = new Carte[3];
        for(int i=0;i<2;i++){
            carti[i]=carte1.ShallowCopy();
            carti2[i]=carte1.deepCopy();
        }
        for(int i=0;i<2;i++){
            carti[i].getCopy();
        }
        for(int i=0;i<2;i++){
            carti2[i].getCopy();
        }
        carte1.verificare();
        for(int i=0;i<2;i++){
            carti[i].getCopy();
        }
        for(int i=0;i<2;i++){
            carti2[i].getCopy();
        }
        //se vede diferenta dintre shallow copy si deep...cand modifica cart1
        //se modifica si elementele din vectorul a carui elemente sunt
        // realizare cu shallow
    }
}