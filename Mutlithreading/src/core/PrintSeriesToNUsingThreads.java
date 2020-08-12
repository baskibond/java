package core;

import lombok.SneakyThrows;


class Lock {
    boolean isEven;
}

class PrinterEven extends Thread {

    Lock lock;//new Object();
    boolean isEven = true;
    int n;

    PrinterEven(int n, Lock lock) {
        this.n = n;
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i <= n; i += 2) {
            synchronized (lock) {

                if (!lock.isEven) {
                    lock.wait();
                }
                System.out.println("Even : " + i);
                lock.isEven = false;
                lock.notify();
            }

        }
    }
}

class PrinterOdd extends Thread {

    Lock lock;//=new Object();
    boolean isOdd = true;
    int n;

    PrinterOdd(int n, Lock lock) {
        this.n = n;
        this.lock = lock;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i <= n; i += 2) {
            synchronized (lock) {
                if (lock.isEven) {
                    lock.wait();
                }
                System.out.println("Odd : " + i);
                lock.isEven = true;
                lock.notify();

            }
        }
    }
}

public class PrintSeriesToNUsingThreads {
    /*
     * We will use two threads to print integer number till N.
     *
     *
     * */

    public static void main(String[] args) {
        Lock lock = new Lock();
        lock.isEven = true;
        PrinterEven printerEven = new PrinterEven(15, lock);
        PrinterOdd printerOdd = new PrinterOdd(15, lock);
        printerEven.start();
        printerOdd.start();


    }
}
