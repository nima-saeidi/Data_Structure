public interface IPositionalList<E> extends ADT {

    IPosition<E> insertFirt(E data);
    IPosition<E> insertLast(E data);
    IPosition<E> insertAfter(E data, IPosition<E> p);
    IPosition<E> insertBefore(E data, IPosition<E> p);

    void set(E data, IPosition<E> p);
    E remove(IPosition<E> p);

    E removeFirst();

    E removeLast();

    E getFirst();

    E getLast();
}