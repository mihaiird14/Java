import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
class Producer implements Runnable {
    private final String nume;

    public Producer(String nume) {
        this.nume = nume;
    }

    @Override
    public void run() {
        boolean succes = false;
        try {
            while (!succes) {
                if (Main.lA.tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(nume + " a blocat BufferA");
                        Thread.sleep(100);

                        if (Main.lB.tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println(nume + " a blocat BufferB");
                                Main.bufferA.add(nume + "-A");
                                Main.bufferB.add(nume + "-B");
                                succes = true;
                                System.out.println(nume + " a produs date");
                            } finally {
                                Main.lB.unlock();
                            }
                        } else {
                            System.out.println(nume + " nu a putut bloca BufferB, reîncearcă...");
                        }
                    } finally {
                        Main.lA.unlock();
                    }
                } else {
                    System.out.println(nume + " nu a putut bloca BufferA, reîncearcă...");
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nume + " a fost întrerupt.");
        }
    }
}

class Consumer implements Runnable {
    private final String nume;

    public Consumer(String nume) {
        this.nume = nume;
    }

    @Override
    public void run() {
        boolean succes = false;
        try {
            while (!succes) {
                if (Main.lB.tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(nume + " a blocat BufferB");
                        Thread.sleep(100);

                        if (Main.lA.tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println(nume + " a blocat BufferA");

                                System.out.println(nume + " a consumat: "
                                        + Main.bufferA.toString() + " & " + Main.bufferB.toString());
                                succes = true;
                            } finally {
                                Main.lA.unlock();
                            }
                        } else {
                            System.out.println(nume + " nu a putut bloca BufferA, reîncearcă...");
                        }
                    } finally {
                        Main.lB.unlock();
                    }
                } else {
                    System.out.println(nume + " nu a putut bloca BufferB, reîncearcă...");
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nume + " a fost întrerupt.");
        }
    }
}
public class Main {
    public static final List<String> bufferA = new ArrayList<>();
    public static final List<String> bufferB = new ArrayList<>();
    public static final ReentrantLock lA = new ReentrantLock();
    public static final ReentrantLock lB = new ReentrantLock();

    public static void main(String[] args) {
        Thread p1 = new Thread(new Producer("P1"));
        Thread p2 = new Thread(new Producer("P2"));
        Thread c1 = new Thread(new Consumer("C1"));
        Thread c2 = new Thread(new Consumer("C2"));

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
