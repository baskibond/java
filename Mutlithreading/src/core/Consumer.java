package core;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Consumer {

    private Broker broker;

    Consumer(Broker broker) {
        this.broker = broker;
    }

    public void consume() {
        Runnable consumerThread = new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    synchronized (broker) {
                        if (broker.getQueue().size() < 1) {
                            System.out.println("Queue is Empty waiting for Producer to act");
                            try {
                                broker.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {

                                System.out.println("Consumer : Consuming an item number :::: " + broker.getQueue().peek());
                                broker.getQueue().poll();
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
        Thread thread = new Thread(consumerThread);
        thread.start();

    }
}
