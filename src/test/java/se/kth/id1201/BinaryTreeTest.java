package se.kth.id1201;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.Integer;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BinaryTreeTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void addSuccessTest()
    {
        Integer root_key = 10;
        Integer left_key = 7;
        Integer right_key = 12;
        Integer left_left_key = 6;
        BinaryTree tree = new BinaryTree();
        tree.add(root_key,0);
        tree.add(left_key,0);
        tree.add(right_key,0);
        tree.add(left_left_key,0);
        assertNotNull(tree.root);
        assertTrue(root_key == tree.root.key);
        assertNotNull(tree.root.left);
        assertTrue(left_key == tree.root.left.key);
        assertNotNull(tree.root.right);
        assertTrue(right_key == tree.root.right.key);
        assertNotNull(tree.root.left.left);
        assertTrue(left_left_key == tree.root.left.left.key);
    }

    @Test
    public void deleteSuccessTest()
    {
        Integer root_key = 10;
        Integer left_key = 7;
        Integer right_key = 12;
        Integer left_left_key = 6;
        BinaryTree tree = new BinaryTree();
        tree.add(root_key,0);
        tree.add(left_key,0);
        tree.add(right_key,0);
        tree.add(left_left_key,0);
        tree.delete(left_key);
        assertNotNull(tree.root);
        assertTrue(root_key == tree.root.key);
        assertNotNull(tree.root.left);
        assertTrue(left_left_key == tree.root.left.key);
        assertNotNull(tree.root.right);
        assertTrue(right_key == tree.root.right.key);
        assertNull(tree.root.left.left);
    }

    @Test
    public void lookUpSuccessTest()
    {
        Integer root_key = 10;
        Integer left_key = 7;
        Integer right_key = 12;
        Integer left_left_key = 6;

        Integer root_value = 10;
        Integer left_value = 7;
        Integer right_value = 12;
        Integer left_left_value = 6;

        BinaryTree tree = new BinaryTree();
        tree.add(root_key,root_value);
        tree.add(left_key,left_value);
        tree.add(right_key,right_value);
        tree.add(left_left_key,left_left_value);

        assertEquals(Integer.compare(tree.lookUp(root_key), root_value), 0);
        assertEquals(Integer.compare(tree.lookUp(left_key), left_value), 0);
        assertEquals(Integer.compare(tree.lookUp(right_key), right_value), 0);
        assertEquals(Integer.compare(tree.lookUp(left_left_key), left_left_value), 0);
    }
}
