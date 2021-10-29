import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public BinaryTreeNode<T> getRoot() {
        return root;
    }
    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        return root == null || root.getElement() == null;
    }

    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> node){
        if (node == null || node.getElement() == null){
            return 0;
        }
        else {
            return size(node.getLeftChild())+size(node.getRightChild())+1;
        }
    }

    public boolean contains(T element){
        return customContains(root, element);
    }

    private boolean customContains(BinaryTreeNode<T> root, T element) {
        if (root == null){
            return false;
        }
        return root.getElement().equals(element) ||
                customContains(root.getLeftChild(),element) || customContains(root.getRightChild(),element);
    }

    public ArrayList<T> inOrder(){
        if (isEmpty()) return null;
        return inOrder(root, new ArrayList<>());
    }

    private ArrayList<T> inOrder(BinaryTreeNode<T> root, ArrayList<T> list){
        if (root == null){
            return list;
        }
        inOrder(root.getLeftChild(),list);
        list.add(root.getElement());
        inOrder(root.getRightChild(),list);
        return list;
    }

    public ArrayList<T> preOrder(){
        if (isEmpty()) return null;
        return preOrder(root, new ArrayList<>());
    }

    private ArrayList<T> preOrder(BinaryTreeNode<T> root, ArrayList<T> list){
        if (root == null){
            return list;
        }
        list.add(root.getElement());
        preOrder(root.getLeftChild(),list);
        preOrder(root.getRightChild(),list);
        return list;
    }

    public ArrayList<T> postOrder(){
        if (isEmpty()) return null;
        return postOrder(root, new ArrayList<T>());
    }

    private ArrayList<T> postOrder(BinaryTreeNode<T> root, ArrayList<T> list) {
        if (root == null){
            return list;
        }
        postOrder(root.getLeftChild(),list);
        postOrder(root.getRightChild(),list);
        list.add(root.getElement());
        return list;
    }

    public ArrayList<T> levelOrder(){
        if (isEmpty()) return null;
        return levelOrder(root, new ArrayList<>());
    }

    private ArrayList<T> levelOrder(BinaryTreeNode<T> root, ArrayList<T> list) {
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            BinaryTreeNode<T> temp = queue.poll();
            list.add(temp.getElement());
            if (temp.getLeftChild() != null){
                queue.add(temp.getLeftChild());
            }
            if (temp.getRightChild() != null){
                queue.add(temp.getRightChild());
            }
        }
        return list;
    }

    public int height(){
        if (isEmpty()) return -1;

        if (root.getLeftChild() == null && root.getRightChild() == null){
            return 0;
        }
        return height(root,0);
    }

    private int height(BinaryTreeNode<T> node, int count){
        if (node == null){
            return count-1;
        }
        int leftHeight = height(node.getLeftChild(),count+1);
        int rightHeight = height(node.getRightChild(),count+1);
        return Math.max(leftHeight, rightHeight);
    }
}
