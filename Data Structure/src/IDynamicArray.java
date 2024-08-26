public interface IDynamicArray<E> extends ADT{

    void add(E data);
    void insert(E data, int index) throws Exception;

    E remove(int index) throws Exception;
    boolean remove(E data) throws Exception;

    E get(int index) throws Exception;
    void set(E data, int index ) throws Exception;

    public static<E> IDynamicArray<E> create(){
        return (IDynamicArray<E>) new DynamicArray<E>();
    }
}
