package hw3;

public class Mirror {
    // пункт 1 ДЗ
    public static char[] mirror(String input){
        int size = input.length();
        if (size == 0) {
            input = "!акортс яатсуП";
            size = 14;
        }
        Stack stack = new Stack(size);
        char[] ch = new char[size];
        for (int i = 0; i < size; i++){
            stack.push(input.charAt(i));
        }
        for (int i = 0; i < size; i++){
            ch[i] = (char) stack.pop();
        }
        return ch;
    }
}
