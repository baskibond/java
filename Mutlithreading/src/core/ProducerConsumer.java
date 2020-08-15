package core;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Broker broker = new Broker(5);
        Producer producer = new Producer(broker);
        producer.produce();
        Consumer consumer = new Consumer(broker);
        consumer.consume();
        //broker.notify();
        Thread.yield();
        System.out.println("Thread count : " + Thread.activeCount());
    }
}
