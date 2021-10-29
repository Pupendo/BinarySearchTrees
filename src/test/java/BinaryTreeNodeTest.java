import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinaryTreeNodeTest {
    private BinaryTreeNode<Object> node;

    @BeforeEach
    public void setUp() {
        node = new BinaryTreeNode<>();
    }


    @Test
    void getAndSetElement() {
        Assertions.assertNull(node.getElement());

        String str = "HelloWorld";
        node.setElement(str);
        Assertions.assertEquals("HelloWorld",node.getElement());

        node.setElement(5);
        Assertions.assertEquals(5,node.getElement());

        Assertions.assertNotEquals("HelloWorld",node.getElement());
    }

    @Test
    void getAndAddLeftChild() {
        BinaryTreeNode<Object> binaryTreeNode = new BinaryTreeNode<>();
        Assertions.assertNull(node.getLeftChild());
        node.addLeftChild(binaryTreeNode);

        Assertions.assertNotNull(node.getLeftChild());
        Assertions.assertEquals(binaryTreeNode,node.getLeftChild());
        Assertions.assertNotEquals(new BinaryTreeNode<>(),node.getLeftChild());
    }

    @Test
    void getAndAddRightChild() {
        BinaryTreeNode<Object> binaryTreeNode = new BinaryTreeNode<>();
        Assertions.assertNull(node.getRightChild());

        node.addRightChild(binaryTreeNode);

        Assertions.assertNotNull(node.getRightChild());
        Assertions.assertEquals(binaryTreeNode,node.getRightChild());
        Assertions.assertNotEquals(new BinaryTreeNode<>(),node.getRightChild());
    }

}
