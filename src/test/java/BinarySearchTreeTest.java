import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

class BinarySearchTreeTest {

    private BinaryTreePrint print;
    private BinarySearchTree<Integer> treeInt;
    private BinarySearchTree<String> treeStr;

    @BeforeEach
    void setUp() {
        print = new BinaryTreePrint();
        treeInt = new BinarySearchTree<>();

        treeInt.insert(7);
        treeInt.insert(2);
        treeInt.insert(3);
        treeInt.insert(5);
        treeInt.insert(13);
        treeInt.insert(7);
        treeInt.insert(11);
        treeInt.insert(19);
        treeInt.insert(17);
        //print.printTree(treeInt.getRoot());

        treeStr = new BinarySearchTree<>();

        treeStr.insert("Pupak");
        treeStr.insert("Saldanha");
        treeStr.insert("Asenova");
        treeStr.insert("Teodosiu");
        treeStr.insert("Peter");
        treeStr.insert("Nikov");
        treeStr.insert("Hornet");
    }

    @Test
    void insertIntegers() {
        // Test if the insert method used in setUp method works as expected.
        // levelOrder method is already tested
        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 13, 3, 11, 19, 5, 17};
        compareList(ints,list);
    }

    @Test
    void insertDuplicateElement() {
        Assertions.assertTrue(treeInt.contains(11));

        System.out.println("BEFORE INSERTING A DUPLICATE ELEMENT:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        //Should do nothing
        treeInt.insert(11);

        System.out.println("AFTER INSERTING A DUPLICATE ELEMENT:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 13, 3, 11, 19, 5, 17};
        compareList(ints,list);
    }

    @Test
    void insertStrings() {
        // Test if the insert method works for other objects (String in this case)
        for (var i: treeStr.levelOrder()) {
            System.out.println(i);
        }
    }

    private <T> void compareList(int[] integers, ArrayList<T> list) {
        if (integers.length != list.size()){
            Assertions.fail();
        }
        for (int i = 0; i < integers.length; i++){
            Assertions.assertEquals(integers[i], list.get(i));
        }
    }

    @Test
    void removeElementNotInTree() {
        Assertions.assertFalse(treeInt.contains(20));

        System.out.println("BEFORE REMOVING AN ELEMENT THAT IS NOT PART OF THE TREE:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        //Should do nothing
        treeInt.removeElement(20);

        System.out.println("AFTER REMOVING AN ELEMENT THAT IS NOT PART OF THE TREE::");
        print.printTree(treeInt.getRoot());
        System.out.println();

        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 13, 3, 11, 19, 5, 17};
        compareList(ints,list);
    }

    @Test
    void removeLeafElement() {
        System.out.println("BEFORE REMOVING A LEAF NODE:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        treeInt.removeElement(17);

        System.out.println("AFTER REMOVING A LEAF NODE:");
        print.printTree(treeInt.getRoot());
        System.out.println();


        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 13, 3, 11, 19, 5};
        compareList(ints, list);
    }

    @Test
    void removeElementWithOneChild() {
        System.out.println("BEFORE REMOVING A NODE WITH ONE CHILD:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        treeInt.removeElement(19);

        System.out.println("AFTER REMOVING A NODE WITH ONE CHILD:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 13, 3, 11, 17, 5};
        compareList(ints, list);
    }

    @Test
    void removeInternalElement() {
        System.out.println("BEFORE REMOVING AN INTERNAL NODE:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        treeInt.removeElement(13);

        System.out.println("AFTER REMOVING AN INTERNAL NODE:");
        print.printTree(treeInt.getRoot());
        System.out.println();

        ArrayList<Integer> list = treeInt.levelOrder();
        int[] ints = {7, 2, 17, 3, 11, 19, 5};
        compareList(ints, list);
    }

    @Test
    void removeRootElement() {
        treeInt = new BinarySearchTree<>();
        treeInt.insert(10);

        print.printTree(treeInt.getRoot());

        treeInt.removeElement(10);

        Assertions.assertTrue(treeInt.isEmpty());
        //print.printTree(treeInt.getRoot());
    }

    @Test
    void findMin() {
        Assertions.assertEquals(2,treeInt.findMin());
        Assertions.assertEquals("Asenova",treeStr.findMin());
    }

    @Test
    void findMax() {
        Assertions.assertEquals(19,treeInt.findMax());
        Assertions.assertEquals("Teodosiu",treeStr.findMax());
    }

    @Test
    void contains() {
        Assertions.assertTrue(treeInt.contains(11));
        Assertions.assertFalse(treeInt.contains(10));
        Assertions.assertTrue(treeStr.contains("Nikov"));
        Assertions.assertFalse(treeStr.contains("Zadravec"));
    }

    @Test
    void rebalancedHeight() {
        for (int i = 0; i < 10; i++){
            BinarySearchTree<Integer> tree = generateBST();

            print.printTree(tree.getRoot());
            System.out.println();
            tree.rebalance();
            print.printTree(tree.getRoot());
            System.out.println();

            int size = tree.size();
            int height = tree.height();

            System.out.println("Size: "+size);
            System.out.println("Height: "+height);

            Assertions.assertEquals((int)(Math.log(size) / Math.log(2)),height);
        }
    }

    @Test
    void rebalance() {
        for (int i = 0; i < 10; i++){
            BinarySearchTree<Integer> searchTree = generateBST();
            System.out.println("BEFORE REBALANCE:");
            print.printTree(searchTree.getRoot());
            System.out.println();

            searchTree.rebalance();

            System.out.println("AFTER REBALANCE:");
            print.printTree(searchTree.getRoot());
            System.out.println();
        }
    }

    private BinarySearchTree<Integer> generateBST(){
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        Random random = new Random();

        int size = random.nextInt(1,100);

        for (int i = 0; i <= size; i++){
          searchTree.insert(random.nextInt(1,101));
        }
        return searchTree;
    }
}