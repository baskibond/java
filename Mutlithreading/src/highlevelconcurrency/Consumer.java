package highlevelconcurrency;

import core.Broker;

import java.util.concurrent.locks.Lock;

public class Consumer {

    private Broker broker;
    private Lock lock;

    Consumer(Broker broker, Lock lock) {
        this.broker = broker;
        this.lock = lock;
    }

    public void consume() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (lock.tryLock()) {
                        if (broker.getQueue().size() == 0) {
                            System.out.println("Queue Empty. Waiting for Producer");
                            //System.out.println("Content of Queue is : "+ Arrays.asList(broker));
                            lock.unlock();
                        } else {
                            System.out.println("Consuming Item  : " + broker.getQueue().peek());
                            broker.getQueue().poll();
                            lock.unlock();
                        }

                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }
}
