public class Lucky {
    static int x = 0;
    static int count = 0;
    static int t1count = 0;
    static int t2count = 0;
    static int t3count = 0;
    private final static Object lock = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x < 999999) {
                synchronized (lock) {
                    x++;
                    if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                            % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                        System.out.println(x);
                        count++;
                    }
                    switch (Thread.currentThread().getName()) {
                        case "1":
                            t1count++;
                            break;
                        case "2":
                            t2count++;
                            break;
                        default:
                            t3count++;
                            break;
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.setName("1");
        t2.setName("2");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
        System.out.println("Thread#1 - " + t1count + ";Thread#2 - " + t2count + ";Thread#3 - " + t3count);
    }
}