import java.util.ArrayList;

//3. Большая задача:
//
//        Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
//        Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//        Для хранения фруктов внутри коробки можно использовать ArrayList;
//        Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
//        Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//        Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
//        Не забываем про метод добавления фрукта в коробку.

public class Box<T extends Box.Fruits> {
    private ArrayList<T> fruits;

    Box(){
        this.fruits = new ArrayList<T>();
    }

    public void add(T fruit) {
        this.fruits.add(fruit);
    }

    public T take() {
        return this.fruits.remove(this.fruits.size() - 1);
    }

    public float getWeight() {
        float result = 0f;
        for (T fruit: fruits) {
            result += fruit.weight;
        }
        return result;
    }

    public void info(){
        System.out.println(fruits.toString());
    }

    public boolean compare(Box<?> otherBox) {
        return (this.getWeight() == otherBox.getWeight());
    }

    public void moveTo(Box<T> otherBox) {
        while (this.fruits.size() > 0) {
            otherBox.add(this.take());
        }

    }


    static abstract class Fruits {
        float weight;
//        static float getWeight() {
//            return weight;
//        }
    }

    static class Apple extends Fruits {
        Apple() {
            this.weight = 1f;
        }
    }

    static class Orange extends Fruits {
        Orange() {
            this.weight = 1.5f;
        }
    }



}
