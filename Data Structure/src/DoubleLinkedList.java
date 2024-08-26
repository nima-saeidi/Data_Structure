import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
//nima saeidi
public class DoubleLinkedList<E> implements LinkedList<E> {
    private static class Node<E>{
        //nima saeidi
        private E m_data;
        private Node m_next;
        private Node m_back;

        public Node(E data, Node next, Node back){
            m_data = data;
            m_next = next;
            m_back=back;
        }


        public E getData(){
            return m_data;
        }

        public Node getNext(){
            return m_next;
        }

        public void setM_back(Node m_back) {
            this.m_back = m_back;
        }

        public Node getM_back() {
            return m_back;
        }

        public void setNext(Node next){
            m_next = next;
        }

        public void setM_data(E m_data) {
            this.m_data = m_data;
        }
    };


    public class DoublyLinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public DoublyLinkedListIterator() {
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

        private final Iterator m_positionalIterator;

        public ElementItrator(){
            m_positionalIterator = getDItrator()  ;
        }

        @Override
        public boolean hasNext() {
            return m_positionalIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_positionalIterator.next();
        }

    }
    private ReentrantLock m_headLock;
    private ReentrantLock m_tailLock;


    private Node m_head;
    private Node m_tail;
    private  AtomicInteger m_size;



    public DoubleLinkedList(){
        m_head = null;
        m_tail = null;
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
        return size()==0;
    }


    @Override
    public void insertFirst(E data) {
        Node newNode=new Node<E>(data,m_head,null);


        m_head=newNode;

        m_size.incrementAndGet();

        if(m_head == null){
            m_headLock.lock();
            m_head = newNode;
            m_headLock.unlock();
        }

        if(m_tail == null){
            m_tail = newNode;
        }


    }


    @Override
    public void insertLast(E data) {
        Node newNode=new Node<>(data,null,m_tail);
        m_tail = newNode;
        m_tail.setNext(newNode);
        m_size.incrementAndGet();
        m_headLock.lock();
        if(m_tail == null){
            m_head = newNode;
        }else {
            newNode.setM_back(null);
            m_tail.setNext(newNode);
        }
        m_headLock.unlock();

    }

    @Override
    public E removeFirst() {
        if (isEmpity()||m_head==null){
            throw new IllegalStateException("empity list");
        }
        E b= (E) m_head.getData();
        m_headLock.lock();
        Node next=m_head.getNext();
        m_head.getNext().setM_back(null);
        m_head=next;
        m_head.setM_data(null);
        m_headLock.unlock();
        m_size.decrementAndGet();
        if (isEmpity()){
            m_tail=null;
        }

        return b;
    }

    @Override
    public E removeLast() {
        if (isEmpity()){
            throw new IllegalStateException("empity list");
        }
        E b= (E) m_tail.getData();
        m_tailLock.lock();
        m_tail=m_tail.getM_back();
        m_tailLock.unlock();
        m_size.decrementAndGet();

        if (size()==1){

            m_tail.setNext(null);
            m_tail.setM_data(null);
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
        return b;
    }

    @Override
    public LinkedList<E> clone(Object other) {


        LinkedList clonedList= LinkedList.create(Type.Double);

        for(Node i = m_head ; i != m_tail;){
            E data = (E) i.getData();
            i = i.getNext();
            assert clonedList != null;
            clonedList.insertLast(data);
        }
        return clonedList;
    }

    @Override
    public boolean egauls(Object other) {
        if(!(other instanceof DoubleLinkedList)){
            return false;
        }else {
            other = (DoubleLinkedList<E>) (other);

            m_headLock.lock();
            Node Head = m_head;
            Node otherHead =  ((DoubleLinkedList<?>) other).getM_head();
            m_headLock.unlock();
            if (!(size() == ((DoubleLinkedList<?>) other).size())) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (Head.getData() != ((DoubleLinkedList<?>) other).getFirst()) return false;
                Head = m_head.getNext();
                otherHead = otherHead.getNext();

            }
        }

        return true;
    }

    @Override
    public boolean isEmpity() {
        return size()==0;
    }

    @Override
    public E getFirst() {
        return (E) m_head.getData();
    }
    @Override
    public String toString(E data){
        return String.format("",data);
    }
    @Override
    public E getLast() {
        return (E) m_tail.getData();
    }

    private Node getM_head() {
        return m_head;
    }


    public Iterator<IPosition<E>> getDItrator(){
        return (Iterator) new DoublyLinkedListIterator<LinkedList<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return new ElementItrator();
    }

}
