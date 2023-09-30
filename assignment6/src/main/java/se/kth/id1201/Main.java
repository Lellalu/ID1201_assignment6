package se.kth.id1201;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        benchmark();
        //treeIterator();
    }

    public static void benchmark(){
        int repitition = 10000;
        List<Integer> k_random_numbers;
        BinaryTree tree;
        int[] sortedArray;
        long startTime;
        long endTime;
        double treeTime;
        double arrayTime;
        Random rand = new Random();

        System.out.printf("#lookUp in a binary tree with length n, time in ns\n");
        System.out.printf("#%12s%12s%12s\n", "n", "BinaryTree", "  BinarySearch");

        for(int size = 4; size < 10000000; size*=2){
            k_random_numbers = new ArrayList<Integer>(size);
            sortedArray = new int[size];
            for(int j = 0; j<size; j++){
                k_random_numbers.add(j, j);
                sortedArray[j] = j;
            }
            Collections.shuffle(k_random_numbers);

            tree = new BinaryTree();
            for(int i = 0; i < size; i++){
                tree.add(k_random_numbers.get(i), k_random_numbers.get(i));
            }

            int randomVal;
            arrayTime = 0;
            startTime = System.nanoTime();
            for(int time = 0; time < repitition; time ++){
                randomVal = rand.nextInt(2*size);
                startTime = System.nanoTime();
                binary_search(sortedArray,randomVal);
                arrayTime += System.nanoTime() - startTime;
            }

            Integer random_key;
            treeTime = 0;
            for(int time = 0; time < repitition; time ++){
                random_key = rand.nextInt(2*size);
                startTime = System.nanoTime();
                tree.lookUp(random_key);
                treeTime += System.nanoTime() - startTime;
            }

            System.out.printf("%13d", size);
            System.out.printf("%12.2f%12.2f\n", treeTime/repitition, arrayTime/repitition);
        }
    }

    public static void treeIterator(){
        BinaryTree tree = new BinaryTree(); 
        tree.add(5,105);
        tree.add(2,102); 
        tree.add(7,107); 
        tree.add(1,101); 
        tree.add(8,108); 
        tree.add(6,106); 
        tree.add(3,103);
        tree.add(4,104); 
        for(int i : tree){
            System.out.println("nextvalue "+i); 
        }
    }

    public static int[] sortedArray(int n){
        Random r = new Random();
        int[] array = new int[n];
        int next = 0;
        for(int i = 0;i < n; i++){
            next += r.nextInt(10)+1;
            array[i]=next;
        }
        return array;
    }

    public static boolean binary_search(int[] array, int key){
        int first=0;
        int last = array.length-1;

        while(true){
            int middle = (first+last)/2;
            if(array[middle]==key){
                return true;
            }

            if(array[middle]<key && middle<last){
                first = middle+1;
                middle = (first+last)/2;
                continue;
            }

            if(array[middle]>key && middle>first){
                last = middle-1;
                middle = (first+last)/2;
                continue;
            }

            return false;
        }
    }
}
