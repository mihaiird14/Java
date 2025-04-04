import java.util.ArrayList;
import java.util.List;
abstract class Vehicul{
    protected String marca;
    protected String model;
    protected int an;
    protected int nrRoti;

    public Vehicul(String marca, String model, int an,int nrRoti){
        this.marca=marca;
        this.model=model;
        this.an=an;
        this.nrRoti=nrRoti;
    }
    public abstract String descriere();
}

class Masina extends Vehicul{
    private boolean cutieAutomanta;
    public Masina(String marca, String model, int an, int nrRoti, boolean cutieAutomanta){
        super(marca,model,an,nrRoti);
        this.cutieAutomanta=cutieAutomanta;
    }

    @Override
    public String descriere(){
        if(cutieAutomanta){
            return "Masina: " + marca+  " " + model + "An fabricatie: " + an + "( " + nrRoti + " roti)" + " Cutie Automata: da";
        }
        else{
            return "Masina: " + marca+  " " + model + "An fabricatie: " + an + "( " + nrRoti + " roti)" + " Cutie Automata: nu";
        }
    }
}

class Motocicleta extends Vehicul{

    private int putere;
    public Motocicleta(String marca, String model, int an, int nrRoti,int putere){
        super(marca,model,an,nrRoti);
        this.putere=putere;
    }
    public String descriere(){
        return "Motocicleta: " + marca+  " " + model + "An fabricatie: " + an + "( " + nrRoti + " roti)" + " Putere: " + putere;
    }
}

class Tir extends Vehicul{

    private int capacitateStocare;
    public Tir(String marca, String model, int an, int nrRoti,int capacitateStocare){
        super(marca,model,an,nrRoti);
        this.capacitateStocare=capacitateStocare;
    }
    public String descriere(){
        return "Tir: " + marca+  " " + model + "An fabricatie: " + an + "( " + nrRoti + " roti)" + " Capacitate: " + capacitateStocare + "tone";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Vehicul>v = new ArrayList<>();
        v.add(new Masina("Dacia","Duster",2023,4,false));
        v.add(new Masina("Tesla","Model 3",2018,4,true));
        v.add(new Motocicleta("Honda","CBR600RR",2021,2,200));
        v.add(new Tir("Scania","R450",2020,10,40));
        for(Vehicul i:v){
            System.out.println(i.descriere());
        }

    }

}