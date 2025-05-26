import java.util.*;
import java.util.concurrent.*;

enum Status {
    RUNNING,
    COMPLETED,
    TIMED_OUT
}

class Task implements Runnable {
    private final int id;
    private final long durata;
    private final Object monitor;
    private final ConcurrentHashMap<Integer, Status> status;
    private final long Tmax;

    public Task(int id, long d, Object monitor,
                ConcurrentHashMap<Integer, Status> statusMap, long Tmax) {
        this.id = id;
        this.durata = d;
        this.monitor = monitor;
        this.status = statusMap;
        this.Tmax = Tmax;
    }

    @Override
    public void run() {
        status.put(id, Status.RUNNING);
        System.out.println("Task " + id + " a inceput.");

        synchronized (monitor) {
            try {
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < durata) {
                    Thread.sleep(100);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                }
                synchronized (status) {
                    status.put(id, Status.COMPLETED);
                }
                System.out.println("Task " + id + " completed.");
            } catch (InterruptedException e) {
                status.put(id, Status.TIMED_OUT);
                System.out.println("Task " + id + " timed out.");
            }
        }
    }
}

class Watchdog extends Thread {
    private final ConcurrentHashMap<Integer, Status> statusMap;

    public Watchdog(ConcurrentHashMap<Integer, Status> statusMap) {
        this.statusMap = statusMap;
    }

    @Override
    public void run() {
        while (true) {
            System.out.printf("%-10s %-15s\n", "Task ID", "Status");
            for (Map.Entry<Integer, Status> entry : statusMap.entrySet()) {
                System.out.printf("%-10d %-15s\n", entry.getKey(), entry.getValue());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        int Tmax = 2000;
        Object monitor = new Object();
        ConcurrentHashMap<Integer, Status> statusMap = new ConcurrentHashMap<>();

        List<Thread> v = new ArrayList<>();
        Watchdog wd = new Watchdog(statusMap);
        wd.start();

        for (int i = 0; i < N; i++) {
            int durata = (i + 1) * 1000;
            Task task = new Task(i, durata, monitor, statusMap, Tmax);
            Thread thread = new Thread(task);
            v.add(thread);
            thread.start();

            long startTime = System.currentTimeMillis();
            while (thread.isAlive() && (System.currentTimeMillis() - startTime < Tmax)) {
                Thread.sleep(100);
            }
            if (thread.isAlive()) {
                thread.interrupt();
                thread.join();
            }
        }
        for (Thread t : v) {
            t.join();
        }
        wd.interrupt();
        wd.join();

        System.out.println("Toate task-urile s-au terminat");
    }
}
