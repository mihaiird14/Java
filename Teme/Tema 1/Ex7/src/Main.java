abstract class Animal{
    public abstract void sunet();
}
abstract class Mamifer extends Animal{}
class Caine extends Mamifer{
    public void sunet(){
        System.out.println("Acest caine latra");
    }
    public void latra(){
        System.out.println("ham ham!");
    }
}
class Pisica extends Mamifer{
    public void sunet(){
        System.out.println("Aceasta pisica miauna!");
    }
    public void meow(){
        System.out.println("Meow!");
    }
}
public class Main {
    public static void main(String[] args) {
        Animal[] a=new Animal[3];
        a[0]= new Pisica();
        a[1]=new Caine();
        a[2]= new Caine();
        for(Animal i:a){
            i.sunet();
            if(i instanceof Caine){
                ((Caine) i).latra();
            }
            else{
                if(i instanceof Pisica){
                    ((Pisica) i).meow();
                }
            }
        }
    }
}