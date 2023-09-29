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
        //benchmark();
        treeIterator();
    }

    public static void benchmark(){
        int repitition = 10000;
        List<Integer> k_random_numbers;
        BinaryTree tree;
        long startTime;
        long endTime;
        Random rand = new Random();

        System.out.printf("#lookUp in a binary tree with length n, time in ns\n");
        System.out.printf("#%12s%12s\n", "n", "time");

        for(int treeSize = 128; treeSize < 1000000; treeSize*=2){
            k_random_numbers = new ArrayList<Integer>(treeSize);
            for(int j = 0; j<treeSize; j++){
                k_random_numbers.add(j, j);
            }
            Collections.shuffle(k_random_numbers);

            tree = new BinaryTree();
            for(int i = 0; i < treeSize; i++){
                tree.add(k_random_numbers.get(i), k_random_numbers.get(i));
            }

            Integer random_key;
            startTime = System.nanoTime();
            for(int time = 0; time < repitition; time ++){
                random_key = rand.nextInt(treeSize);
                //startTime = System.nanoTime();
                tree.lookUp(random_key);
                //endTime = System.nanoTime();
            }
            endTime = System.nanoTime();

            System.out.printf("%13d", treeSize);
            System.out.printf("%12d\n", (endTime-startTime)/repitition);
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
}
