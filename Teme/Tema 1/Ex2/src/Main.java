import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String citire = scanner.nextLine();
        String[] numere = citire.split(" ");
        if(numere.length < 5){
            System.out.println("Trebuie cel putin 5 numere!");
        }
        else{
            int[] v=new int[numere.length];
            for(int i=0;i<numere.length;i++){
                v[i]=Integer.parseInt(numere[i]);
            }

            Integer[] v2 = new Integer[v.length];
            for(int i=0;i<v.length;i++){
                v2[i]=v[i];
            }

            System.out.println("Vector initial: " + Arrays.toString(v2));
            Arrays.sort(v2);
            System.out.println("Sortare: " + Arrays.toString(v2));

            String nrInput = scanner.nextLine();
            int nr = Integer.parseInt(nrInput);

            int gasit = Arrays.binarySearch(v2,nr);
            if(gasit >=0){
                System.out.println("Elementul exista!");
            }
            else{
                System.out.println("Elementul nu exista!");
            }
        }
    }
}