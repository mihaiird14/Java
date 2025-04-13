import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Logger{
    private static Logger log;
    private Logger(){}
    public static Logger getLog(){
        if(log==null){
            log=new Logger();
        }
        return log;
    }

    private String getTimp(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(format);
    }

    public void logInfo(String s){
        System.out.println(getTimp() + " - " + s);
    }
    public void logWarinig(String s){
        System.out.println(getTimp()+" !!WARNING!! " + s);
    }
    public void logError(String s){
        System.out.println(getTimp()+ " !!EROARE!! " + s);
    }
}

public class Main {
    public static void main(String[] args) {
        Logger l1=Logger.getLog();
        Logger l2=Logger.getLog();
        System.out.println(l1==l2); //verificare aceeasi instanta
        l1.logInfo("Ok");
        l2.logWarinig("Apuca-te de proiect!");
        l1.logError("Ai uitat punct si virgula!");
    }
}