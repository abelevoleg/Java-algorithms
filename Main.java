package hw2;

public class Main {
    public static void main(String[] args) {
        Array array = new Array(15, 14, 13, 12, 11, 10, 9, 8, 7, 1);
        Array array1 = new Array(15, 14, 13, 12, 11, 10, 9, 8, 7, 1);
        Array array2 = new Array(15, 14, 13, 12, 11, 10, 9, 8, 7, 1);
        Array array3 = new Array(15, 14, 13, 12, 11, 10, 9, 8, 7, 1);
        Array array4 = new Array(1, 2, 1, 2, 1, 2, 0, 2, 2, 1);
        array.sortBubble();
        array1.sortBubbleBetter();
        array2.sortSelect();
        array3.sortInsert();
        array4.sortCount();
        System.out.println(array);
        System.out.println(array1);
        System.out.println(array2);
        System.out.println(array3);
        System.out.println(array4);
    }
}
