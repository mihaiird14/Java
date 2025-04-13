package Tabla;
public class Tabla {
    String[] p1= new String[4];
    String[] p2= new String[4];
    String[] p3= new String[4];
    String[] p4= new String[4];
    public Tabla(){
        p1[0]="           ";
        p2[0]="           ";
        p3[0]="           ";
        p4[0]="           ";
        p1[1]="           ";
        p2[1]="           ";
        p3[1]="           ";
        p4[1]="           ";
        p1[2]="           ";
        p2[2]="           ";
        p3[2]="           ";
        p4[2]="           ";
        p1[3]="           ";
        p2[3]="           ";
        p3[3]="           ";
        p4[3]="           ";
    }
    public void setTabla(int poz,String[] linii){
        if(poz==1){
            p1[0]=linii[0];
            p1[1]=linii[1];
            p1[2]=linii[2];
            p1[3]=linii[3];
        } else{
            if(poz==2){
                p2[0]=linii[0];
                p2[1]=linii[1];
                p2[2]=linii[2];
                p2[3]=linii[3];
            }
            else{
                if(poz==3){
                    p3[0]=linii[0];
                    p3[1]=linii[1];
                    p3[2]=linii[2];
                    p3[3]=linii[3];
                }
                else{
                    p4[0]=linii[0];
                    p4[1]=linii[1];
                    p4[2]=linii[2];
                    p4[3]=linii[3];
                }
            }
        }
    }
    public void afisTabla(){
        System.out.println("             "+p2[0]);
        System.out.println("             "+p2[1]);
        System.out.println("             "+p2[2]);
        System.out.println("             "+p2[3]);
        System.out.println(p1[0]+"               "+p3[0]);
        System.out.println(p1[1]+"               "+p3[1]);
        System.out.println(p1[2]+"               "+p3[2]);
        System.out.println(p1[3]+"               "+p3[3]);
        System.out.println("             "+p4[0]);
        System.out.println("             "+p4[1]);
        System.out.println("             "+p4[2]);
        System.out.println("             "+p4[3]);
    }
}
