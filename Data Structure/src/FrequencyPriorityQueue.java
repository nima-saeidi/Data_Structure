import java.util.*;
import java.util.LinkedList;

//nima saeidi
class FrequencyPriorityQueue<T> {
    private final List<T> list;
    private final Map<T, Integer> map;
    public FrequencyPriorityQueue() {
        list = new LinkedList<>();
        map = new HashMap<>();
    }
    public void add(T element) {
        map.put(element, map.getOrDefault(element, 0) + 1);
        list.add(element);
        list.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));
    }
    public T remove() {
        if (list.isEmpty()) {
            return null;
        }
        T element = list.remove(0);
        int frequency = map.get(element) - 1;
        if (frequency == 0) {
            map.remove(element);
        } else {
            map.put(element, frequency);
        }
        return element;
    }
    public T peek() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public static void main(String[] args) {
        FrequencyPriorityQueue<Integer> queue = new FrequencyPriorityQueue<>();
        queue.add(7);
        queue.add(1);
        queue.add(1);
        queue.add(7);
        queue.add(2);
        queue.add(0);
        queue.add(1);
        queue.add(2);
        queue.add(7);
        queue.add(5);
        queue.add(6);
        queue.add(3);
        queue.add(4);
        queue.add(7);
        queue.add(6);
        queue.add(5);
        queue.add(1);
        System.out.println("Integer");
        while (queue.peek() != null) {
            System.out.println(queue.remove());
        }
        FrequencyPriorityQueue<String> queue1 = new FrequencyPriorityQueue<>();
        queue1.add("apple");
        queue1.add("banana");
        queue1.add("apple");
        queue1.add("cherry");
        queue1.add("cherry");
        queue1.add("cherry");
        queue1.add("cherry");
        queue1.add("banana");
        queue1.add("banana");
        System.out.println("String");
        while (!queue1.isEmpty()) {
            System.out.println(queue1.remove());
        }

    }
}