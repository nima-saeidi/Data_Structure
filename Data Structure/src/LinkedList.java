
//nima saeidi
public  interface LinkedList<E> extends ADT {
    void insertFirst(E data);
    void insertLast(E data);
    E removeFirst();
    E removeLast();

    LinkedList<E> clone(Object other);

    boolean egauls(Object other);

    boolean isEmpity();

    E getFirst();

    String toString(E data);

    E getLast();
    static enum Type{Single,Double};



    static LinkedList create(Type type){

        return new SingleLinkedList<Integer>();


    };
    /*
    todo [1]: define static enum named "Type"
    */


    /*
    todo [2]: define static function named "create" get a Type and return a LinkedList<E>.
              thid function act as factory to create SingleLinkedList
    */
}
