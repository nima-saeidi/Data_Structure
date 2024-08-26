import java.util.Iterator;

public interface IPriorityQueue<E,K> extends ADT {

    Iterator<E> getElementIterator();

    void insert(E data, K key);
    E remove() throws Exception;
    E top();
}