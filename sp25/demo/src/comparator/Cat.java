package comparator;

import java.util.Comparator;

public class Cat {

    private static class CatComparator implements Comparator<Cat> {
        @Override
        public int compare(Cat o1, Cat o2) {
            return o1.size.compareTo(o2.size);
        }
    }

    public static Comparator<Cat> catComparator = new CatComparator();
    public String name;
    public Integer size;

    public Cat(String name, Integer size) {
        this.name = name;
        this.size = size;
    }
}
