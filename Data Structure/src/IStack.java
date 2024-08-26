public interface IStack<E> extends ADT {
    E pop();
    void push(E data);
    E top();
}
