package highlevelconcurrency;

import core.Broker;
import lombok.Data;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

@Data
public class Producer {
    private Broker broker;
    private Lock lock;

    Producer(Broker broker, Lock lock) {
        this.broker = broker;
        this.lock = lock;
    }

    public void produce() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (lock.tryLock()) {
                        if (broker.getQueue().size() == broker.getCAPACITY()) {
                            System.out.println("Queue full. Waiting for Consumer");
                            System.out.println("Content of Queue is : " + Arrays.asList(broker));
                            lock.unlock();
                        } else {
                            int i = (int) (Math.random() * 1000);
                            System.out.println("Adding New item : " + i);
                            broker.getQueue().add("" + i);
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
