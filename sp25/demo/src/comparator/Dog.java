package comparator;

public class Dog implements Comparable<Dog> {
    public String name;
    public Integer size;

    public Dog(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int compareTo(Dog o) {
        return this.size - o.size;
    }
}
