import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements LinkedList<E> {
    //nima saeidi

    private static class Node<E>{

        private E m_data;
        private Node m_next;

        public Node(E data, Node next){
            m_data = data;
            m_next = next;
        }


        public E getData(){
            return m_data;
        }

        public Node getNext(){
            return m_next;
        }

        public void setNext(Node next){
            m_next = next;
        }

        public void setM_data(E m_data) {
            this.m_data = m_data;
        }
    };

    public class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public LinkedListIterator() {
            current = m_head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }

            T data = current.m_data;
            current = current.m_next;
            return data;
        }
    }

    class ElementItrator<E> implements Iterator<E>{

        private final Iterator<LinkedList<E>> m_SIterator;

        public ElementItrator(){
            m_SIterator =(LinkedListIterator) getSingleItrator();
        }

        @Override
        public boolean hasNext() {
            return m_SIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_SIterator.next();
        }

    }


    private Node m_head;
    private Node m_tail;
    private int m_size;

    public SingleLinkedList(){
        m_head = null;
        m_tail = null;
        m_size = 0;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void insertFirst(E data) {

        Node newNode = new Node(data,m_head);
        m_head = newNode;

        m_size++;

        if(m_tail == null){
            m_tail = newNode;
        }
    }

    @Override
    public void insertLast(E data) {
        Node newNode = new Node(data,null);



        m_size++;

        if(m_head == null){
            m_head = newNode;
        }
        else {
            m_tail.setNext(newNode);
        }
        m_tail = newNode;
    }

    @Override
    public E removeFirst() {

        E b= (E) m_head.getData();

        m_head.setM_data(null);
        m_head=m_head.getNext();
        m_tail.setNext(null);
        m_size--;
        if (m_head.getNext()==m_head){
            m_head.setNext(null);
        }

        return b;
    }

    @Override
    public E removeLast() {

        E b= (E) m_tail.getData();
        m_tail.setNext(null);
        m_tail.setM_data(null);
        m_size--;
        if (m_tail.getNext()==m_tail){
            m_tail.setNext(null);
        }
        return b;
    }

    @Override
    public LinkedList<E> clone(Object other) {
        return null;
    }

    @Override
    public boolean egauls(Object other) {
        return false;
    }


    @Override
    public E getFirst() {

        return (E) m_head.getData();

    }

    @Override
    public String toString(E data) {
        return null;
    }

    @Override
    public E getLast() {

        return (E) m_tail.getData();
    }
    @Override
    public boolean isEmpity(){
        return size()==0;
    }

    public Iterator<IPosition<E>> getSingleItrator(){
        return (LinkedListIterator) new LinkedListIterator<LinkedList<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return new ElementItrator();
    }

}
