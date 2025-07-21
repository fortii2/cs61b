package TreeTraversals;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;


public class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void BFS() {
        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(this);
        BFS_helper(queue);
    }

    private void BFS_helper(Queue<Node<T>> queue) {
        if (queue.isEmpty()) {
            return;
        }

        Node<T> current = queue.dequeue();

        if (current.left != null) {
            queue.enqueue(current.left);
        }

        if (current.right != null) {
            queue.enqueue(current.right);
        }

        System.out.print(current.value + " ");

        BFS_helper(queue);
    }
}
