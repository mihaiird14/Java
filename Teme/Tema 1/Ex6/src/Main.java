class Profesor{
    private String nume;
    private String specializare;
    private final String codId;
    public Profesor(String nume,String specializare,String codId){
        this.nume=nume;
        this.specializare=specializare;
        this.codId=codId;
    }
    public String getNume(){
        return nume;
    }
    public String getSpecializare(){
        return specializare;
    }
    public void setNume(String s){
        this.nume=s;
    }
    public void setSpecializare(String s){
        this.specializare=s;
    }
}
class Curs{
    private Profesor profesor;
    private String[] studenti;
    public Curs(Profesor profesor,String[] studenti){
        this.profesor=profesor;
        this.studenti=studenti;
    }
    public Profesor getProf(){
        return profesor;
    }
    public String[] getStudenti(){
        return studenti;
    }
    public void setProf(Profesor s){
        profesor=s;
    }
    public void setStudenti(String[] s){
        studenti=s;
    }
    public void afis(){
        System.out.println("Profesor: "+profesor.getNume());
        System.out.println("Studenti: ");
        for(String i:studenti){
            System.out.println(i+" ");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Profesor p =new Profesor("Pop Ion","Informatica","abcd");
        String[] st={"Nume1","Nume2","Nume3","Nume4"};
        Curs c=new Curs(p,st);
        c.afis();
        p.setNume("Pop2");
        c.afis();
        System.out.println(p.getNume());
        System.out.println(p.getSpecializare());
        for(String i:c.getStudenti()){
            System.out.println(i);
        }
    }
}