import java.util.Objects;
import java.util.Arrays;
final class Student implements Comparable<Student>{
    private final String nume;
    private final int varsta;
    private final double medie;
    public Student(String nume,int varsta,double medie){
        this.nume=nume;
        this.varsta=varsta;
        this.medie=medie;
    }
    public String getNume(){
        return nume;
    }
    public int getVarsta(){
        return varsta;
    }
    public double getMedie(){
        return medie;
    }

    public int compareTo(Student s){
        return Double.compare(s.medie,this.medie);
    }
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(!(obj instanceof Student)){
            return false;
        }
        Student st= (Student)obj;
        return varsta==st.varsta && Double.compare(st.medie,medie)==0 && Objects.equals(nume,st.nume);

    }
    public int hashCode(){
        return Objects.hash(nume,varsta,medie);
    }
    public String toString(){
        return "Nume student: "+nume+" varsta: "+varsta+" media: "+medie;
    }
}
class Grup<T extends Student>{
    private T[] studenti;
    private int numar;
    public Grup(int cnt){
        studenti=(T[]) new Student[cnt];
        numar=0;
    }
    public void addStudent(T student){
        if(numar>= studenti.length){
            System.out.println("Nu mai este loc in grup");
        }
        else{

            studenti[numar]=student;
            numar++;
        }
    }
    public T cauta(String nume){
        for(T i:studenti){
            if(i.getNume().equalsIgnoreCase(nume)){
                return i;
            }
        }
        return null;
    }
    public void sort(){
        Arrays.sort(studenti,0,numar);
    }
    public StringBuilder raportTextual(){
        StringBuilder raport=new StringBuilder();
        for(int i=0;i<numar;i++){
            raport.append(studenti[i].toString()).append("\n");
        }
        return raport;
    }
    public synchronized StringBuffer raportTextualSync(){
        StringBuffer raport=new StringBuffer();
        for(int i=0;i<numar;i++){
            raport.append(studenti[i].toString()).append("\n");
        }
        return raport;
    }
}
public class Main {
    public static void main(String[] args) {
        Grup<Student> gr=new Grup<>(4);
        gr.addStudent(new Student("Popescu",18,7));
        gr.addStudent(new Student("Ion",19,9.7));
        gr.addStudent(new Student("Alex",20,8.6));
        System.out.println(gr.raportTextual());
        gr.sort();
        System.out.println(gr.raportTextual());
        Student x=gr.cauta("popescu");
        if(x!=null){
            System.out.println("Student gasit: "+x);
        }
        else{
            System.out.println("Studentul nu a fost gasit!");
        }
    }
}