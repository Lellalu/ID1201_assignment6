package se.kth.id1201;

import java.util.Stack;
import java.util.Iterator;

public class BinaryTree implements Iterable<Integer>{
    public class Node{ 
        public Integer key;
        public Integer value; 
        public Node left, right; 

        public Node(Integer key, Integer value){ 
            this.key = key; 
            this.value = value; 
            this.left = null;
            this.right = null; 
        } 

        public void add (Integer key, Integer value){
            if(this.key.intValue() == key.intValue()){
                this.value = value;
                return;
            }
            if(this.key.intValue() > key.intValue()){
                if(this.left != null){
                    this.left.add(key, value);
                } else{
                    this.left = new Node(key, value);
                }
            }else{
                if(this.right != null){
                    this.right.add(key, value);
                } else{
                    this.right = new Node(key, value);
                }
            }
        }

        public Node delete(Integer k){
            if(this.key.intValue() == k.intValue()){
               if (this.left == null){
                return this.right;
               }
               if (this.right == null){
                return this.left;
               }
               Node promoted = this.right.promote();
               promoted.left = this.left;
               return promoted;
            }
            if(this.key.intValue() < k.intValue() && this.right !=null){
                right = this.right.delete(k);
                return this;
            }
            if(this.key.intValue() > k.intValue() && this.left !=null){
                left = this.left.delete(k);
                return this;
            }
            return this;
        }

        public Node promote(){
            if(this.left == null){
                return this;
            }
            Node cur = this;
            while(cur.left != null){
                cur = cur.left;
            }
            Node ret = cur;
            this.left = cur.right;
            cur.right = this;
            return ret;
        }

        public void print(){ 
            if(left != null) 
                left.print(); 
            System.out.println("key:" + key + "\t value:" + value); 
            if(right!=null)
                right.print(); 
        }
    }

    Node root;
        
    public BinaryTree(){ 
        root = null;
    }

    public void add (Integer key, Integer value){
        if (root == null){
            root = new Node(key, value);
        }
        else{
            root.add(key, value);
        }
    }

    public Integer lookUp(Integer key){
        Node cur = this.root;
        while(cur != null){
            if(cur.key.intValue() == key.intValue()){
                return cur.value;
            }
            if(key.intValue() < cur.key.intValue()){
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
        return null;
    }

    public void delete(Integer key){
        if(root == null)
            return;
        root = root.delete(key);
    }

    public Iterator<Integer> iterator(){
        return new TreeIterator(); 
    }

    public class TreeIterator implements Iterator<Integer>{
        private Stack <Node> stack;

        public TreeIterator(){
            stack = new Stack<Node>();
            Node node = root;
            while(node != null){
                stack.push(node);
                node = node.left;
            }
        }

        @Override 
        public boolean hasNext(){ 
            return !stack.empty();
        }

        @Override 
        public Integer next(){
            Node next_node = stack.pop();
            Node node;
            if(next_node.right != null){
                node = next_node.right;
                while(node != null){
                    stack.push(node);
                    node = node.left;
                }
            }
            return next_node.key;
        } 

        @Override 
        public void remove(){ 
            throw new UnsupportedOperationException(); 
        } 
    }
}
