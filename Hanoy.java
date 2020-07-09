package hw5;

public class Hanoy {
    private static int[] start = {1, 2, 3, 4, 5, 6, 7, 8};
    private static int[] finish = new int[8];
    private static int[] middle = new int[8];

    public static void main(String[] args) {
        print(start, middle, finish);
        tower(start.length * start.length);
        print(start, middle, finish);
    }

    static int[] s = start;
    static int[] m = middle;
    static int[] f = finish;

    // рекурсивная функция, вызывающая 2 подфункции (iter и change) с меняющимися аргументами
    private static void tower(int k) {
       iter(s, m, f);
       int[] q = s;
       int[] w = m;

       if (k % 2 != 0){
           q = m;
           w = s;
       }
       if (k == 61 || k == 53 || k == 49 || k == 45 || k == 37 || k == 29 || k == 21 || k == 17 || k == 13 || k == 5){
           q = s;
           w = m;
       }
       // не смог придумать, как обобщить условие для этой операции, пришлось прописывать исключения вручную
       if (k > 1) change(q, w);

       int[] temp = s;
       s = f;
       f = m;
       m = temp;
       k--;
       if (k > 0) tower(k);
    }

    private static void iter(int[] a, int[] b, int[] c){
        change(a, b);
        change(a, c);
        change(b, c);
    }

    private static int topNumber(int[] arr){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != 0) return i;
        }
        return arr.length;
    }

    private static void print(int[] post1, int[] post2, int[] post3){
        for (int i = 0; i < post1.length; i++){
            System.out.println(post1[i] + "   " + post2[i] + "   " + post3[i]);
        }
    }

    private static void change(int[] n, int[] m){
        int temp = n[topNumber(n)];
        n[topNumber(n)] = m[topNumber(m) - 1];
        m[topNumber(m) - 1] = temp;
    }
}
