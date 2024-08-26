import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
//nima saeidi
public class PositionalList<E> implements IPositionalList<E> {


    private class PositionalItrator<I> implements Iterator< IPosition<E> > {

        private Node<E> m_cursor;

        public PositionalItrator(){
            m_cursor = m_head;
        }


        @Override
        public boolean hasNext(){
            return m_cursor.getNext() != m_tail;
        }

        @Override
        public IPosition<E> next(){
            m_cursor = m_cursor.getNext();
            return m_cursor;
        }


    }

    class ElementItrator<E> implements Iterator<E>{

        private final Iterator<IPosition<E>> m_positionalIterator;

        public ElementItrator(){
            m_positionalIterator =(PositionalItrator) getPositionalItrator();
        }

        @Override
        public boolean hasNext() {
            return m_positionalIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_positionalIterator.next().getData();
        }

    }



    private static class Node<T> implements IPosition<T>{

        private T m_data;
        private Node<T> m_next;

        public Node(T data, Node<T> next){
            m_data = data;
            m_next = next;
        }

        @Override
        public T getData(){
            return m_data;
        }

        public Node<T> getNext(){
            return m_next;
        }

        public void setNext(Node<T> next){
            m_next = next;
        }


    };
    ReentrantLock m_headLock;
    ReentrantLock m_tailLock;


    private Node<E> m_head;
    private Node<E> m_tail;
    AtomicInteger m_size;

    public PositionalList(){
        m_tail = new Node<E>(null,null);
        m_head = new Node<E>(null,m_tail);

        m_size = new AtomicInteger(0);

        m_headLock = new ReentrantLock();
        m_tailLock = new ReentrantLock();

    }

    @Override
    public int size() {
        return m_size.get();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public IPosition<E> insertFirt(E data) {

        Node<E> newNode = new Node(data,m_head);

        m_headLock.lock();
        m_head = newNode;
        m_headLock.unlock();

        m_size.incrementAndGet();

        if(m_tail == null){
            m_tail = newNode;
        }

        return newNode;
    }

    @Override
    public IPosition<E> insertLast(E data) {
        Node<E> newNode = new Node(data,null);
        m_tail.setNext(newNode);
        m_tail = newNode;

        m_size.incrementAndGet();

        m_headLock.lock();
        if(m_head == null){
            m_head = newNode;
        }
        m_headLock.unlock();

        return newNode;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()){
            System.out.println("error");
        }
        E data=m_head.getData();
        m_size.decrementAndGet();
        m_head=null;
        if (m_head==m_tail){
            return null;
        }
         return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            System.out.println("error");
        }
        E data=m_tail.getData();
        m_size.decrementAndGet();

        if (size()==1){

            m_tail.setNext(null);

            m_tail=null;
        }else {
            Node notlast=m_head;
            while (notlast.getNext()!=m_tail){
                notlast=notlast.getNext();
            }
            m_tail=notlast;
            notlast.setNext(null);
        }


        if (m_tail.getNext()==m_tail){
            m_tail.setNext(null);
        }
        return data;
    }

    @Override
    public E getFirst() {

        return m_head.getData();
    }

    @Override
    public E getLast() {

        return m_tail.getData();
    }


    @Override
    public boolean equals(Object other){

        if(!(other instanceof SingleLinkedList)){
            return false;
        }
          other=(LinkedList<E>)other;
        if (!(((SingleLinkedList<?>) other).size()==size())){
            return false;
        }
        Node<E> c=m_head;
        for (int i = 0; i < size(); i++){
            if (c!=c.getNext()){
                return false;
            }
            c=c.getNext();
        }
        return true;
    }

    public  LinkedList<E>  clone(){

        LinkedList<E> clonedList= LinkedList.create(LinkedList.Type.Single);

        for(Node i = m_head ; i != m_tail;){
            E data = (E) i.getData();
            i = i.getNext();
            clonedList.insertLast(data);
        }
        return clonedList;

    }

    @Override
    public IPosition<E> insertAfter(E data, IPosition<E> p) {

        Node<E> node = validatePosition(p);
        if(node == null){
            return null;
        }

        Node<E> next = node.getNext();
        Node<E> newNode = new Node(data,next);
        node.setNext(newNode);

        m_size.incrementAndGet();

        return newNode;
    }

    @Override
    public IPosition<E> insertBefore(E data, IPosition<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertBefore'");
    }

    @Override
    public void set(E data, IPosition<E> p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public E remove(IPosition<E> p) {
        Node<E> node = validatePosition(p);
        if(node == null){
            return null;
        }

        //todo:  find prev node and update list
        node.setNext(null);
        return node.getData();
    }

    Node<E> validatePosition(IPosition<E> p){

        if(!(p instanceof Node)){
            return null;
        }

        Node<E> node = (Node<E>)p;
        if(node.getNext() == null){
            return null;
        }

        return node;
    }



    public Iterator<IPosition<E>> getPositionalItrator(){
        return new PositionalItrator<IPosition<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return new ElementItrator();
    }


}

