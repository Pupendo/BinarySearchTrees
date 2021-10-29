import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

    private BinarySearchTreeNode<T> root;

    public void insert(T element){
        if (!contains(element)){
            insert(getRoot(), element);
        }
    }

    private void insert(BinaryTreeNode<T> node, T element) {
        if (node == null){
            root = new BinarySearchTreeNode<>();
            root.setElement(element);
            setRoot(root);
            return;
        }

        if (node.getElement().compareTo(element) <= 0){
            if (node.getRightChild() == null){
                BinarySearchTreeNode<T> child = new BinarySearchTreeNode<>();
                child.setElement(element);
                node.addRightChild(child);
            }
            else{
                insert(node.getRightChild(),element);
            }
        }
        else {
            if (node.getLeftChild() == null){
                BinarySearchTreeNode<T> child = new BinarySearchTreeNode<>();
                child.setElement(element);
                node.addLeftChild(child);
            }
            else{
                insert(node.getLeftChild(),element);
            }
        }
    }

    public void removeElement(T element){
        if (contains(element)){
            removeElement(getRoot(),element);
        }
    }

    private BinaryTreeNode<T> removeElement(BinaryTreeNode<T> root, T element) {
        if (root == null){
            return null;
        }
        int compare = element.compareTo(root.getElement());

        if (compare < 0){
            root.addLeftChild(removeElement(root.getLeftChild(),element));
        }
        else if (compare > 0) {
            root.addRightChild(removeElement(root.getRightChild(),element));
        }
        else {
            // A leaf node
            if (root.getLeftChild() == null && root.getRightChild() == null){
                return null;
            }
            //Node with one child
            else if (root.getLeftChild() == null){
                return root.getRightChild();
            }
            else if (root.getRightChild() == null){
                return root.getLeftChild();
            }
            //Internal node
            else {
                T min = findMin(root.getRightChild());
                root.setElement(min);
                root.addRightChild(removeElement(root.getRightChild(),min));
            }
        }
        return root;
    }

    public T findMin(){
        return findMin(getRoot());
    }

    private T findMin(BinaryTreeNode<T> root) {
        return root.getLeftChild() == null ? root.getElement() : findMin(root.getLeftChild());
    }

    public T findMax(){
        return findMax(getRoot());
    }

    private T findMax(BinaryTreeNode<T> root) {
        return root.getRightChild() == null ? root.getElement() : findMax(root.getRightChild());
    }

    public boolean contains(T element){
        return contains(getRoot(),element) != null;
    }

    private BinaryTreeNode<T> contains(BinaryTreeNode<T> root, T element) {
        if (root == null){
            return null;
        }
        if (root.getElement().compareTo(element) == 0){
            return root;
        }
        return root.getElement().compareTo(element) < 0 ? contains(root.getRightChild(),element) : contains(root.getLeftChild(),element);
    }

    public void rebalance(){
        ArrayList<T> inOrder =  inOrder();
        setRoot(buildBST(toArray(inOrder)));
    }

    private BinarySearchTreeNode<T> buildBST(T[] list){
        if (list.length == 0){
            return null;
        }
        int index = list.length/2;
        BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>();
        node.setElement(list[index]);
        node.addLeftChild(buildBST(Arrays.copyOfRange(list,0,index)));
        node.addRightChild(buildBST(Arrays.copyOfRange(list,index+1,list.length)));
        return node;
    }

    private <T> T[] toArray(List<T> list) {
        T[] toR = (T[]) java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size());
        for (int i = 0; i < list.size(); i++) {
            toR[i] = list.get(i);
        }
        return toR;
    }

}
