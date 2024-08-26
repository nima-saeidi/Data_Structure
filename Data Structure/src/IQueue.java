public interface IQueue<E> extends ADT {
    void enqueue(E data) throws Exception;
    E dequeue() throws Exception;
    E first() throws Exception;
}