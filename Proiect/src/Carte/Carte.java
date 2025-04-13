package Carte;

public class Carte{
    private String semn;
    private String valoare;
    private int valComp;
    public Carte(String semn,String valoare,int valComp){
        this.semn=semn;
        this.valoare=valoare;
        this.valComp=valComp;
    }
    public int getValComp(){return valComp;}
    public String getSemn(){
        return semn;
    }
    public String getValoare(){
        return valoare;
    }
    public String[] desen(){
        String linie1;
        String linie2;
        String linie3;
        String linie4;
        if(semn.equals("I.ROSIE")){
            linie1="-----------";
            //System.out.println(linie1);
            linie2="--"+semn+"--";
            //System.out.println(linie2);
            if(!valoare.equals("AS") && !valoare.equals("10")) {
                linie3="-----" + valoare + "-----";
                //System.out.println(linie3);
            }
            else{
                linie3="-----" + valoare + "----";
                //System.out.println(linie3);
            }
            linie4="-----------";
            //System.out.println(linie4);
        }
        else{
            if(semn.equals("CARO")){
                linie1="-----------";
                //System.out.println(linie1);
                linie2="---"+semn+"----";
                //System.out.println(linie2);
                if(!valoare.equals("AS") && !valoare.equals("10")) {
                    linie3="-----" + valoare + "-----";
                    //System.out.println(linie3);
                }
                else{
                    linie3="-----" + valoare + "----";
                    //System.out.println(linie3);
                }
                linie4="-----------";
                //System.out.println(linie4);
            }
            else{
                if(semn.equals("TREFLA")){
                    linie1="-----------";
                    //System.out.println(linie1);
                    linie2="---"+semn+"--";
                    //System.out.println(linie2);
                    if(!valoare.equals("AS") && !valoare.equals("10")) {
                        linie3="-----" + valoare + "-----";
                        //System.out.println(linie3);
                    }
                    else{
                        linie3="-----" + valoare + "----";
                        //System.out.println(linie3);
                    }
                    linie4="-----------";
                    //System.out.println(linie4);
                }
                else{
                    linie1="-----------";
                    //System.out.println(linie1);
                    linie2="---"+semn+"--";
                   // System.out.println(linie2);
                    if(!valoare.equals("AS") && !valoare.equals("10")) {
                        linie3="-----" + valoare + "-----";
                        //System.out.println(linie3);
                    }
                    else{
                        linie3="-----" + valoare + "----";
                        //System.out.println(linie3);
                    }
                    linie4="-----------";
                    //System.out.println(linie4);
                }
            }
        }
        return new String[]{linie1,linie2,linie3,linie4};
    }
}
