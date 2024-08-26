import java.util.Iterator;
import java.util.NoSuchElementException;
//nima saeidi
public class DynamicArray<E> implements IDynamicArray<E> {

    public class DynamicArrayItrator<E> implements Iterator<E> {
        private E[] dynamicArray;
        private int currentIndex;

        public DynamicArrayItrator() {
            this.dynamicArray = dynamicArray;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < dynamicArray.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return dynamicArray[currentIndex++];
        }


    }

    class ElementItrator<E> implements Iterator<E>{

        private final Iterator<IPosition<E>> m_DAIterator;

        public ElementItrator(){
            m_DAIterator =(DynamicArrayItrator) getDynamicArrrayItrator();
        }

        @Override
        public boolean hasNext() {
            return m_DAIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) m_DAIterator.next().getData();
        }

    }








    private int m_size;
    private E[] m_content;

    public DynamicArray(){
        m_size = 0;
        m_content = (E[]) new Object[100]; //todo
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
    public void add(E data) {
        check4resize();
        m_content[m_size++] = data;
    }

    @Override
    public void insert(E data, int index) throws Exception {



        check4resize();

        for(int i = m_size -1 ; i >= index ; i--){
            m_content[i+1] = m_content[i];
        }
        m_content[index] = data;
    }

    @Override
    public E remove(int index) throws Exception {

        if(index >= m_size){
            throw new Exception("");
        }

        E returnVal = m_content[index];

        for(int i = index ; i < m_size - 1 ; i++){
            m_content[i] = m_content[i + 1];
        }

        m_size--;
        return returnVal;
    }

    @Override
    public boolean remove(E data) throws Exception {
        int position = search(data);
        if(position < 0){
            return false;
        }

        remove(position);
        return true;
    }

    @Override
    public E get(int index) throws Exception {
        if(index >= m_size || index < 0){
            throw new Exception("");
        }
        return m_content[index];
    }


    private void check4resize(){
        if(m_size != m_content.length)
            return;

        E[] newContent = (E[]) new Object[m_content.length * 2];

        for(int i = 0 ; i < m_content.length ; i++){
            newContent[i] = m_content[i];
        }

        m_content = newContent;
    }

    private int search(E data){

        for(int  i = 0 ; i < m_size ; i++){
            if( m_content[i].equals(data)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(E data, int index) throws Exception {
        if(index >= m_size){
            throw new Exception();
        }

        m_content[index] = data;
    }



    public DynamicArrayItrator<IDynamicArray<E>> getDynamicArrrayItrator(){
        return new DynamicArrayItrator<IDynamicArray<E>>();
    }

    public Iterator<E> getEleentItrator(){
        return (Iterator<E>) new ElementItrator<IDynamicArray<E>>();
    }


}
