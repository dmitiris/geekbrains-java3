import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // testing first task
        String[] test = {"The", "bear", "hugged", "the", "hunter"};
        System.out.println(Arrays.toString(test));
        changeElements(test, 1,4);
        System.out.println(Arrays.toString(test));

        // testing second task
        Integer[] first = {1,2,3 ,4,5};
        ArrayList abc = transformArrayToList(first);
        String[] second = {"abc", "def", "ghi"};
        ArrayList def = transformArrayToList(second);

        // testing third task
        Box<Box.Apple> appleBox = new Box<Box.Apple>();
        Box<Box.Orange> orangeBox1 = new Box<Box.Orange>();
        Box<Box.Orange> orangeBox2 = new Box<Box.Orange>();
        Box.Apple apple1 = new Box.Apple();
        Box.Orange orange1 = new Box.Orange();

        appleBox.add(apple1);
        appleBox.add(apple1);
        appleBox.add(apple1);
//        appleBox.add(orange1);
        System.out.println(appleBox.getWeight());

        orangeBox1.add(orange1);
        orangeBox1.add(orange1);
        System.out.println(orangeBox1.getWeight());
        System.out.println(orangeBox2.getWeight());

        System.out.println(orangeBox1.compare(appleBox));
        System.out.println(orangeBox1.compare(orangeBox2));

        orangeBox1.info();
        orangeBox2.info();

        orangeBox1.moveTo(orangeBox2);
        orangeBox1.info();
        orangeBox2.info();
        orangeBox1.moveTo(orangeBox2);
        orangeBox1.info();
        orangeBox2.info();

    }

    // 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    private static <T> void changeElements(T[] array, int elementIndex1, int elementIndex2) {
        T temp = array[elementIndex1];
        array[elementIndex1] = array[elementIndex2];
        array[elementIndex2] = temp;
    }

    // 2. Написать метод, который преобразует массив в ArrayList;
    private static <T> ArrayList<T> transformArrayToList(T[] array) {
        if (array.length > 0) {
            System.out.println(array[0].getClass());
        }
        return new ArrayList<T>(Arrays.asList(array));
    }
}
