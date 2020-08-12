package core;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class Broker {
    private Queue<String> queue = new LinkedList<>();
    private int CAPACITY;
    private int size;

    Broker(int capacity) {
        this.CAPACITY = capacity;
        queue = new LinkedList<>();
        size = 0;
    }
}
