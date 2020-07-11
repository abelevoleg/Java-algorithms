package hw6;

import hw4.Cat;

public class Main {
    public static void main(String[] args) {
        Tree[] catTree = new Tree[20];
        for (int i = 0; i < 20; i++) {
            catTree[i] = new Tree();
            int number = 0;
            while (number < 7) {
                int age = (int) (Math.random() * 200 - 100);
                Cat cat = new Cat(age, "Barsik");
                catTree[i].insert(cat);
                number = catTree[i].numberLevel(cat.getAge());
                if (number == 7) catTree[i].delete(cat.getAge());
            }
        }

        int counter = 0;
        for (int i = 0; i < 20; i++) {
            if (!catTree[i].isBalanced(catTree[i].root)) counter++;
        }
        System.out.println(counter * 5 + "% несбалансированных деревьев");
    }
}
