import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyAListTest {

    @Test
    public void addAndGetLastTest(){
        MyAList lst = new MyAList();
        lst.addLast(0);
        Assertions.assertEquals(0, lst.getLast());
        lst.addLast(1);
        Assertions.assertEquals(1, lst.getLast());
        lst.addLast(2);
        Assertions.assertEquals(2, lst.getLast());
    }

    @Test
    public void sizeTest(){
        MyAList lst = new MyAList();
        Assertions.assertEquals(0, lst.size());

        lst.addLast(0);
        lst.addLast(1);
        lst.addLast(2);
        Assertions.assertEquals(3, lst.size());
    }

    @Test
    public void getTest(){
        MyAList lst = new MyAList();
        lst.addLast(0);
        lst.addLast(1);
        lst.addLast(2);

        Assertions.assertEquals(0, lst.get(0));
        Assertions.assertEquals(1, lst.get(1));
        Assertions.assertEquals(2, lst.get(2));
    }

    @Test
    public void removeLastTest(){
        MyAList lst = new MyAList();
        lst.addLast(0);
        lst.addLast(1);
        lst.addLast(2);

        lst.removeLast();
        Assertions.assertEquals(2, lst.size());
        Assertions.assertEquals(1, lst.getLast());
    }
}
