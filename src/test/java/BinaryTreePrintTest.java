import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreePrintTest {

    private BinaryTreePrint print;
    private BinaryTree<Object> tree;

    @BeforeEach
    void setUp() {
        print = new BinaryTreePrint();
        tree = new BinaryTree<>();
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
    void printTree() {
        print.printTree(tree.getRoot());
    }
}