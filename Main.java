package hw3;

import static hw3.Mirror.mirror;
import static hw3.Test.test;

public class Main {
    public static void main(String[] args) {
        String q = "А роза упала на лапу Азора";
        String w = "";
        System.out.println(mirror(q));
        System.out.println(mirror(w));

        String e = "0123456789";
        System.out.println(test(e));

        PriorityQueue pr = new PriorityQueue(5);
        pr.insert(1, 1);
        pr.insert(2, 1);
        pr.insert(3, 1);
        pr.insert(4, 1);
        pr.insert(5, 1);
        pr.remove();
        pr.remove();
        pr.insert(6, 0);
        pr.insert(7, -1);
        for (int i = 0; i < 5; i++)
            System.out.println(pr.data(i));
    }
}
