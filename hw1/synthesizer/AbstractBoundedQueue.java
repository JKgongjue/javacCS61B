package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int getFillCount() {
        return fillCount;
    }

    public int getCapacity() {
        return capacity;
    }
}
