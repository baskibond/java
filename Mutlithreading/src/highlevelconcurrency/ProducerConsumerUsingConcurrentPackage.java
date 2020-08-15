package highlevelconcurrency;

import core.Broker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingConcurrentPackage {
    public static void main(String[] args) {
        Broker broker = new Broker(5);
        final Lock lock = new ReentrantLock();
        Producer producer = new Producer(broker, lock);
        producer.produce();
        Consumer consumer = new Consumer(broker, lock);
        consumer.consume();
    }
}
