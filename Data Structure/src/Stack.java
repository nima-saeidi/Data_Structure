import java.util.Iterator;
import java.util.NoSuchElementException;
//nima saeidi
public class Stack<E> implements IStack<E> {


    private class StackItrator<I> implements Iterator<Stack<E> > {

        private Stack<E> stack;

        public StackItrator() {
            this.stack = stack;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Stack<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (Stack<E>) stack.pop();
        }



    }

    class ElementItrator<E> implements Iterator<E>{

        private final Iterator<IPosition<E>> m_StackIterator;

        public ElementItrator(){
            m_StackIterator =(StackItrator) getStackItrator();
        }

        @Override
        public boolean hasNext() {
            return m_StackIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_StackIterator.next().getData();
        }

    }

    LinkedList<E> m_content;

    public Stack(){
        m_content = new SingleLinkedList<>();
    }

    @Override
    public int size() {
        return m_content.size();
    }

    @Override
    public boolean isEmpty() {
      return m_content.isEmpty();
    }

    @Override
    public E pop() {
        return m_content.removeFirst();
    }

    @Override
    public void push(E data) {
        m_content.insertFirst(data);
    }

    @Override
    public E top() {
        return m_content.getFirst();
    }


    public StackItrator<IStack<E>> getStackItrator(){
        return new StackItrator<IStack<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return new ElementItrator<>();
    }


}