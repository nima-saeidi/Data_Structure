
import java.util.Comparator;
import java.util.Iterator;


public class PriorityQueue<E,K> implements IPriorityQueue<E,K> {


    private class PEntiry implements Entity<E,K>{

        E m_data;
        K m_key;

        public PEntiry(E data, K key){
            m_data = data;
            m_key = key;
        }

        @Override
        public E getData() {
            return m_data;
        }

        @Override
        public K getKey() {
            return m_key;
        }

    }


    SingleLinkedList<E> m_content;
    Comparator<K> m_comp;


    public PriorityQueue(){
        m_content = new SingleLinkedList<>();

        m_comp  = (o1, o2) -> ((Comparable)o1).compareTo(o2);
    }


    public PriorityQueue(Comparator<K> comp){
        this();
        m_comp = comp;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean isEmpty() {
        return m_content.isEmpty();
    }

    @Override
    public Iterator<E> getElementIterator() {
        return m_content.getEleentItrator();
    }

    @Override
    public void insert(E data, K key) {
        m_content.insertFirst((E) new PEntiry(data, key));
    }

    @Override
    public E remove() throws Exception {
        Iterator it = m_content.getEleentItrator();

        if(!it.hasNext()){
            throw new Exception();
        }

        PEntiry max = (PEntiry)it.next();

        while (it.hasNext()) {
            PEntiry en = (PEntiry)it.next();

            if(m_comp.compare(max.getKey(), en.getKey())< 0){
                max = en;
            }
        }

        return max.getData();

    }

    @Override
    public E top() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'top'");
    }

}
