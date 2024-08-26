import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //nima saeidi

    public static void main(String[] args) throws Exception {
        Scanner np=new Scanner(System.in);
        System.out.println("1. ArrayList");
        System.out.println("2. double LinkedList");
        System.out.println("3. single linkedList");
        System.out.println("4. Queue");
        System.out.println("5. stack");
        System.out.println("6. positional list");
        System.out.println("7. dynamic array");
        System.out.println("enter number:");
        int choice=np.nextInt();

        switch (choice){
            case 1:{
                ArrayList<Integer> dataStructure = new ArrayList<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.add(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 2:{
                DoubleLinkedList<Integer> dataStructure = new DoubleLinkedList<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.insertFirst(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 3:{
                SingleLinkedList<Integer> dataStructure = new SingleLinkedList<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.insertFirst(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 4:{
                Queue<Integer> dataStructure = new Queue<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.enqueue(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 5:{
                Stack<Integer> dataStructure = new Stack<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.push(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 6:{
                PositionalList<Integer> dataStructure = new PositionalList<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.insertFirt(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
            case 7:{
                DynamicArray<Integer> dataStructure = new DynamicArray<>();

                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    dataStructure.add(random.nextInt(100));
                }
                Iterator<Integer> iterator = dataStructure.getEleentItrator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }

        }


    }
    }
