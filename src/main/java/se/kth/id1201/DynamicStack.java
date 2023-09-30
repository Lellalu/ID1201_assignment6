package se.kth.id1201;

/**
* The dynamic stack class in the program extends the Stack abstract class, which  
* constructs stacks that can grow by expanding size as we add more items, and 
* shrink down the size when we reduce items.
*/
public class DynamicStack extends Stack {
    public class StackEmptyException extends RuntimeException {}

    private final int MINIMUM_SIZE;
    private int size;
    private int[] valueStack;
    private int stackPointer;  //pointing to the position of current item.

    /**
    * @param initialSize The initial size of the stack.
    */
    public DynamicStack(int initialSize){
        this.MINIMUM_SIZE = initialSize;
        this.size = initialSize;
        this.valueStack = new int[size];
        this.stackPointer = -1; //Pointing to the current position of value.
    }

    /**
    * Push the value in stack.
    * If the stack is full, expand the size as doubble of the original stack, 
    * and then push the value into the stack.
    * @param i int value to put into the stack.
    */
    public void push(int i) {
        if(stackPointer > size-2){
            expandAndCopy();
        }
        stackPointer++;
        valueStack[stackPointer] = i;
    }

    /**
     * To expand and copy the value stack as two times.
     */
    private void expandAndCopy(){
        int[] expandedValueStack = new int[2*size];

        for(int i = 0;i<size;i++){
            expandedValueStack[i]=valueStack[i];
        }
        this.valueStack = expandedValueStack;
        this.size = this.size*2;
    }

    /**
    * Pop the value on top out of the stack.
    * If the current size is larger tha the minimum size and the current pointer 
    * is pointing to the position which is smaller than 1/4 of current size, the 
    * stack size will be shrink down to half of it and then pop the value on the top out.
    * @return the int value on top of the stack.
    */
    public int pop() {
        if(stackPointer == -1){
            throw new StackEmptyException();
        }   
        if(stackPointer <= size/4 && size >= MINIMUM_SIZE){
            shrinkAndCopy();
        }
        int value = valueStack[stackPointer];
        stackPointer--;
        return value;
    }
    
    /**
     * To shrink and copy the value stack as 1/2 of the original size.
     */
    private void shrinkAndCopy(){
        int[] shrinkValueStack = new int[size/2];

        for(int i = 0;i<size/2;i++){
            shrinkValueStack[i]=valueStack[i];
        }
        this.valueStack = shrinkValueStack;
        this.size = this.size/2;
    }

}