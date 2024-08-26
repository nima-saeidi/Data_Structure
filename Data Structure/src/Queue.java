import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

//nima saeidi
public class Queue<E> implements IQueue<E> {

    private class QueueItrator<I> implements Iterator<Queue<E>> {
        private Queue<E> queue;
        public QueueItrator() {

        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Queue<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            try {
                return (Queue<E>) queue.dequeue();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }




    }

    class ElementItrator<E> implements Iterator<E>{

        private final Queue<E>.QueueItrator<IQueue<E>> m_QueueIterator;

        public ElementItrator(){
            m_QueueIterator =(QueueItrator) getQueueItrator();
        }

        @Override
        public boolean hasNext() {
            return m_QueueIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_QueueIterator.next();
        }

    }


        private final IDynamicArray<E> m_content;
        private final AtomicInteger m_start;
        private final AtomicInteger m_end;
        ReentrantLock m_startLock;
        ReentrantLock m_endLock;


        public Queue(){
            m_start=new AtomicInteger(0);
            m_end=new AtomicInteger(0);
            m_content = IDynamicArray.create();
            m_endLock=new ReentrantLock();
            m_startLock=new ReentrantLock();

        }

        @Override
        public int size() {
            if(m_start.get() < m_end.get()){
                return m_end.get() - m_start.get();
            }
            else{
                return m_content.size() - (m_start.get() - m_end.get());
            }
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public void enqueue(E data) throws Exception {
            if(isFull()){
                throw new Exception();
            }

            m_endLock.lock();
            m_content.insert(data, m_end.get());
            m_endLock.unlock();
            m_end.incrementAndGet();


            if(m_end.get() == m_content.size() && m_start.get() > 0){
                m_end.set(0);
            }
            if (isEmpty()) m_start.set(m_end.get());

        }

        @Override
        public E dequeue() throws Exception {

            m_startLock.lock();

            m_content.remove(m_start.get());
            m_startLock.unlock();

            m_start.set((m_start.get()+1)%m_content.size());
            m_end.decrementAndGet();
            if(m_start.get() == m_content.size() && m_end.get() > 0){
                m_start.set(0);
            }


            return m_content.remove(m_start.get());

        }
        public String toString() {
            StringBuilder str= new StringBuilder();
            for (int i = 0; i < m_content.size(); i++) {
                try {
                    str.append(m_content.get(i));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return str.toString();
        }

        @Override
        public E first() throws Exception {
            return m_content.get(m_start.get());
        }

        private boolean isFull(){
            return m_end.get() == size() - 1;
        }



    public QueueItrator<IQueue<E>> getQueueItrator(){
        return new QueueItrator<IQueue<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return new ElementItrator<>();
    }

}
