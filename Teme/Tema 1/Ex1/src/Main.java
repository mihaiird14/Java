//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static boolean checkInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        if(args.length<3){
            System.out.println("Prea putine argumente!");
        }

        else{
            int nrInt=0;
            int nrDouble = 0;
            int ok = 1;
            int a=0;
            int b=0;
            double c=0.0;
            for(String x:args){
                if(checkInt(x)){
                    nrInt+=1;
                    if(nrInt==1){
                        a= Integer.parseInt(x);
                    }
                    else{
                        b= Integer.parseInt(x);
                    }
                }
                else{
                    if(checkDouble(x)){
                        nrDouble+=1;
                        c = Double.parseDouble(x);
                    }
                    else{
                        ok = 0;
                    }
                }
            }
            switch(ok){
                case 0:
                    System.out.println("Nu toti parametrii sunt numere valide!");
                    break;
                default:
                    if(nrInt>1 && nrDouble>0){
                        Integer nr1 = a;
                        Integer nr2 = b;
                        Double nr3 = c;

                        double suma = a+b+c;
                        double medie = (a+b+c)/3;
                        double produs = a*b*c;

                        System.out.println("Suma: " + suma);
                        System.out.println("Medie: " + medie);
                        System.out.println("Produs: " + produs);
                    }
                    else{
                        System.out.println("Trebuie sa existe cel putin 2 nr intregi si cel putin unul real!");

                    }
            }
        }
    }
}