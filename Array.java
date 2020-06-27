package hw2;

public class Array {
    private int arr[];
    private int size;
    private boolean isSorted;

    private Array() {
        this.isSorted = false;
    }

    public Array(int capacity) {
        this();
        arr = new int[capacity];
        this.size = 0;
    }

    public Array(int... args) {
        this();
        this.size = args.length;
        this.arr = args;
    }

    public int get(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        return arr[index];
    }

    public void set (int index, int value) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        arr[index] = value;
    }

    public int length() {
        return size;
    }

    private void increaseCapacity() {
        int[] temp = arr;
        arr = new int[size * 2];
        System.arraycopy(temp, 0, arr, 0, size);
    }

    public void append(int value) {
        if (size >= arr.length) {
            increaseCapacity();
        }
        arr[size++] = value;
        isSorted = false;
    }

    public int deleteLast() {
        if (size == 0)
            throw new ArrayIndexOutOfBoundsException(-1);

        return arr[--size];
    }

    // homework
    // метод 1 - вставка со сдвигом элементов вправо
    public void insert(int index, int value) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        append(arr[size - 1]);
        for (int i = size - 1; i > index; i--){
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
    }

    // метод 2 - удаление элемента заданной величины
    public void deleteValue(int val){
        int number = find(val);
        if (number == -1)
            throw new ArrayIndexOutOfBoundsException("Такого элемента нет!");
        for (int i = number; i < size; i++){
            arr[i] = arr[i + 1];
        }
        deleteLast();
    }

    // метод 3 - удаление элемента заданного индекса
    public void deleteIndex(int index){
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
        for (int i = index; i < size; i++){
            arr[i] = arr[i + 1];
        }
        deleteLast();
    }

    // метод 4 - удаление всех элементов массива
    public void deleteAll(){
        size = 0;
    }

    @Override
    public String toString() {
        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }

    public int find(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    public boolean hasValue(int value) {
        if (!isSorted)
            throw new RuntimeException("try the 'find' method");

        int l = 0;
        int r = size;
        int m;
        while (l < r) {
            m = (l + r) >> 1; // (l + r) / 2
            if (value == arr[m])
                return true;
            else if (value < arr[m])
                r = m;
            else
                l = m + 1;
        }
        return false;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void sortBubble() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
                count++;
            }
        }
        isSorted = true;
        System.out.println(count);
        // подсчитанное количество операций - 90. Соответствует n^2, на 10 меньше 100 получается за счет того, что
        // второй цикл идет до size-1.
    }

    // улучшенная пузырьковая сортировка
    /*
    * первоначально у нас каждый проход первого цикла (по i) вытаскивает максимальный пузырек "наверх". Если
    * добавить к нему, что дополнительно он будет вытаскивать минимальный пузырек "вниз", то проходов первого цикла
    * потребуется в два раза меньше и условие цикла (i < size) можно будет заменить на условие (i < size/2), что
    * уменьшит количество проходов в 2 раза. Сложность соответственно уменьшится с n^2 до (n^2)/2.
    * */

    public void sortBubbleBetter() {
        int count = 0;
        for (int i = 0; i < (size / 2); i++) {
            for (int j = 0; j < size - 2; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
                if (arr[size - j - 1] < arr[size - j - 2]) {
                    swap(size - j - 1, size - j - 2);
                }
                count++;
            }
        }
        isSorted = true;
        System.out.println(count);
        // подсчитанное количество операций - 40. Соответствует (n^2)/2, на 10 меньше 50 получается за счет того, что
        // второй цикл идет до size-2.
    }

    public void sortSelect() {
        int count = 0;
        for (int flag = 0; flag < size; flag++) {
            int cMin = flag;
            for (int rem = flag + 1; rem < size; rem++) {
                if (arr[rem] < arr[cMin])
                    cMin = rem;
                count++;
            }
            swap(flag, cMin);
        }
        isSorted = true;
        System.out.println(count);
        // подсчитанное количество операций - 45. Соответствует (n^2)/2, на 5 меньше 50 получается за счет того, что
        // второй цикл начинается с flag+1.
    }

    public void sortInsert() {
        int count = 0;
        for (int out = 0; out < size; out++) {
            int temp = arr[out];
            int in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
                count++;
            }
            arr[in] = temp;
        }
        isSorted = true;
        System.out.println(count);
        // подсчитанное количество операций менялось от 16 до 45 в зависимости от положения элементов в массиве.
        // Соответствует (n^2)/2, уменьшалось за счет того, что while позволяет уменьшить количество циклов при
        // более удачном положении элементов в массиве.
    }


    // сортировка подсчетом (п.4 дз)
    // сложность получается 3n, подсчитанное количество операций = 30 (во второй цикл по j в последнем цикле
    // при нулевом количестве элементов программа не заходит, т.к. не выполняется условие по j.
        public void sortCount() {
        int count = 0;
        Array temp = new Array(arr.length);
        for (int i = 0; i < arr.length; i++){
            temp.append(0);
            count++;
        }
        for (int i = 0; i < arr.length; i++){
            temp.set(arr[i], temp.get(arr[i]) + 1);
            count++;
        }

        int m = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < temp.get(i); j++){
                set(m, i);
                m++;
                count++;
            }
        }
        System.out.println(count);
    }
}
