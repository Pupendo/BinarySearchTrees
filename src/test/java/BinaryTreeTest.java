import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BinaryTreeTest {
    private BinaryTree<Object> tree;
    private BinaryTreePrint print;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
        print = new BinaryTreePrint();
        BinaryTreeNode<Object> node = new BinaryTreeNode<>();
        BinaryTreeNode<Object> node1 = new BinaryTreeNode<>();
        BinaryTreeNode<Object> node2 = new BinaryTreeNode<>();
        BinaryTreeNode<Object> node3 = new BinaryTreeNode<>();
        BinaryTreeNode<Object> node4 = new BinaryTreeNode<>();

        node.setElement(2);
        node1.setElement(3);
        node2.setElement(5);
        node3.setElement(7);
        node4.setElement(11);

        tree.setRoot(node);
        tree.getRoot().addLeftChild(node1);
        tree.getRoot().addRightChild(node2);
        tree.getRoot().getLeftChild().addLeftChild(node3);
        tree.getRoot().getLeftChild().addRightChild(node4);
    }


    @Test
    void isEmpty() {
        tree = new BinaryTree<>();
        Assertions.assertTrue(tree.isEmpty());

        tree.setRoot(new BinaryTreeNode<>());
        Assertions.assertTrue(tree.isEmpty());

        tree.getRoot().setElement(true);
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void sizeZeroElements() {
        // The root is null
        tree = new BinaryTree<>();
        Assertions.assertEquals(0,tree.size());

        // The root is instantiated but contains no element
        tree.setRoot(new BinaryTreeNode<>());
        Assertions.assertEquals(0,tree.size());
    }

    @Test
    void sizeOneElements() {
        tree.setRoot(new BinaryTreeNode<>());
        tree.getRoot().setElement(23);
        Assertions.assertEquals(1,tree.size());
    }

    @Test
    void sizeManyElements() {
        Assertions.assertEquals(5,tree.size());
    }

    @Test
    void contains() {
        //Testing for Integers
        Assertions.assertTrue(tree.contains(11));
        Assertions.assertFalse(tree.contains(12));

        //Testing for Strings
        BinaryTreeNode<Object> node = new BinaryTreeNode<>();
        node.setElement("Hello World");
        tree.getRoot().getRightChild().addLeftChild(node);
        Assertions.assertTrue(tree.contains("Hello World"));
        Assertions.assertFalse(tree.contains("Hell"));
    }

    @Test
    void emptyTree() {
        //Test if all traversals return null when the tree is empty
        tree = new BinaryTree<>();
        Assertions.assertNull(tree.inOrder());
        Assertions.assertNull(tree.postOrder());
        Assertions.assertNull(tree.preOrder());

        Assertions.assertNull(tree.levelOrder());
        //Test if height method returns -1 when the tree is empty
        Assertions.assertEquals(-1,tree.height());
    }

    @Test
    void inOrder() {
        print.printTree(tree.getRoot());
        Object[] integers = new Object[]{7, 3, 11, 2, 5};
        ArrayList<Object> list = tree.inOrder();
        System.out.println();
        System.out.print("InOrder: ");

        compareList(integers, list);
    }

    @Test
    void preOrder() {
        print.printTree(tree.getRoot());
        Object[] integers = new Object[]{2,3,7,11,5};
        ArrayList<Object> list = tree.preOrder();
        System.out.println();
        System.out.print("PreOrder: ");

        compareList(integers, list);
    }

    @Test
    void postOrder() {
        print.printTree(tree.getRoot());
        Object[] integers = new Object[]{7,11,3,5,2};
        ArrayList<Object> list = tree.postOrder();
        System.out.println();
        System.out.print("PostOrder: ");

        compareList(integers, list);
    }

    @Test
    void levelOrder() {
        print.printTree(tree.getRoot());
        Object[] integers = new Object[]{2,3,5,7,11};
        ArrayList<Object> list = tree.levelOrder();
        System.out.println();
        System.out.print("LevelOrder: ");

        compareList(integers, list);
    }

    private void compareList(Object[] integers, ArrayList<Object> list) {
        for (int i = 0; i < integers.length; i++){
            System.out.print(list.get(i)+" ");
            Assertions.assertEquals(integers[i], list.get(i));
        }
    }

    @Test
    void heightZero() {
        tree = new BinaryTree<>();
        BinaryTreeNode<Object> node = new BinaryTreeNode<>();
        node.setElement(23);
        tree.setRoot(node);
        print.printTree(tree.getRoot());

        Assertions.assertEquals(0,tree.height());
    }

    @Test
    void heightOne() {
        tree = new BinaryTree<>();
        BinaryTreeNode<Object> node = new BinaryTreeNode<>();
        node.setElement(32);
        tree.setRoot(node);

        BinaryTreeNode<Object> node1 = new BinaryTreeNode<>();
        node1.setElement(23);
        tree.getRoot().addRightChild(node1);
        print.printTree(tree.getRoot());

        Assertions.assertEquals(1,tree.height());
    }

    @Test
    void heightMany() {
        print.printTree(tree.getRoot());

        Assertions.assertEquals(2,tree.height());

        BinaryTreeNode<Object> node = new BinaryTreeNode<>();
        node.setElement(13);
        tree.getRoot().getLeftChild().getLeftChild().addRightChild(node);
        print.printTree(tree.getRoot());

        Assertions.assertEquals(3,tree.height());

        tree.getRoot().getLeftChild().getRightChild().addRightChild(node);
        print.printTree(tree.getRoot());
        Assertions.assertEquals(3,tree.height());
    }
}
