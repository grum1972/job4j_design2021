package ru.job4j.collection;

public class SimpleQueue<T> {

    private int capacityIn = 0;
    private int capacityOut = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (capacityOut == 0) {
            while (capacityIn > 0) {
                out.push(in.pop());
                capacityIn--;
                capacityOut++;
            }
        }
        capacityOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        capacityIn++;
    }
}
