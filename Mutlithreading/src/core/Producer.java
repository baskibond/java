package core;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Producer {
    private Broker broker;

    Producer(Broker broker) {
        this.broker = broker;
    }

    public void produce() {
        Runnable producerRunnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                while (true) {
                    synchronized (broker) {
                        if (broker.getQueue().size() == broker.getCAPACITY()) {
                            System.out.println("Queue is full waiting for Consumer to act");
                            try {
                                broker.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {

                                System.out.println("Producer: Adding item number " + broker.getQueue().size());
                                broker.getQueue().add("" + broker.getQueue().size());
                                broker.notify();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Thread.sleep((long) (Math.random() * (1000 - 1 + 1) + 1));
                }
            }
        };

        Thread thread = new Thread(producerRunnable);
        thread.start();
    }
}
