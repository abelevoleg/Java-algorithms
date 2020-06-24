package hw1;

public class Algorithm {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        int c = 4;
        int[] array = {3, 2, 1, 4, 5};

        System.out.println(exp(b, c));
        System.out.println(expParity(a, 7));
        System.out.println(min(array));
        System.out.println(average(array));
    }

    /*
    простое возведение в степень. Сложность линейная О(n), так как количество итераций растет линейно пропорционально
    росту показателя степени y (или как критерий - содержит один цикл for).
     */
    public static int exp(int x, int y){
        int result = 1;
        for (int i = 0; i < y; i++){
            result = result * x;
        }
        return result;

    }

     /*
     возведение в степень с использование четности. Сложность логарифмическая О(logn), так как благодаря
     делению величины степени на 2 при каждой итерации (кроме случаев нечетной величины степени), их общее
     количество будет расти при росте показателя степени по логарифмическому закону.
      */
    public static int expParity(int x, int y) {
        int z = 1;

        while (y > 0) {
            if (y == 0) {
                x = x * x;
                y = y / 2;
            } else {
                z = z * x;
                --y;
            }
        }
        return z;
    }

    /*
    поиск минимального элемента в массиве. Сложность линейная О(logn), так как количество итераций линейно растет
    соответственно росту количества элементов массива (массив один раз перебирается полностью).
     */
    public static int min(int[] arr){
        int min = arr[0];
        for (int i = 0; i < arr.length; i++){
            if (min > arr[i]) min = arr[i];
        }
        return min;
    }

    /*
    нахождение среднего арифметического в массиве. Сложность линейная О(logn), так как количество итераций линейно растет
    соответственно росту количества элементов массива (массив один раз перебирается полностью).
     */
    public static double average(int[] arr){
        int summ = 0;
        for (int i = 0; i < arr.length; i++){
            summ = summ + arr[i];
        }
        double aver = summ / arr.length;
        return aver;
    }
}
