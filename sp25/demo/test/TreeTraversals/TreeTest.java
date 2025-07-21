package TreeTraversals;

import org.junit.jupiter.api.Test;

public class TreeTest {
    @Test
    public void BFS_Test() {
        Node<Character> root = new Node<>('D',
                new Node<>('B', new Node<>('A', null, null), new Node<>('C', null, null)),
                new Node<>('F', new Node<>('E', null, null), new Node<>('G', null, null)));

        root.BFS();
    }
}