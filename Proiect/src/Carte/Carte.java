package Carte;

public class Carte{
    private String semn;
    private String valoare;
    public Carte(String semn,String valoare){
        this.semn=semn;
        this.valoare=valoare;
    }
    public String getSemn(){
        return semn;
    }
    public String getValoare(){
        return valoare;
    }
}
