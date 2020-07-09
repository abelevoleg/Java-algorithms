package hw5;

// 8 ферзей. Решение сделано на базе программы для хода конем с добавлением модуля на случай если ходы конем
// заканчиваются, а возможные поля еще есть
public class Queens {
    public static void main(String[] args) {
        int[][] desk = new int[8][8];
        nextQueen(desk, 1, 0, 1);
        printDesk(desk);
        System.out.println(op);
    }

    private static int[][] kMoves = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk.length &&
                y >= 0 && y < desk[0].length &&
                desk[x][y] == 0;
    }

    private static int op = 0;
    private static boolean nextQueen(int[][] desk, int currX, int currY, int move) {
        for (int i = 0; i < desk.length; i++){
            // в поля, находящиеся под ударом устанавливаются значения -1
            desk[currX][i] = -1;
            desk[i][currY] = -1;
            if (isPossible(desk, currX + i, currY + i)){
                desk[currX + i][currY + i] = -1;
            }
            if (isPossible(desk, currX - i, currY - i)){
                desk[currX - i][currY - i] = -1;
            }
            if (isPossible(desk, currX + i, currY - i)){
                desk[currX + i][currY - i] = -1;
            }
            if (isPossible(desk, currX - i, currY + i)){
                desk[currX - i][currY + i] = -1;
            }
        }
        desk[currX][currY] = move;
        // 8 ферзей для доски 8х8
        if (move == desk.length) return true;

        int nextX, nextY;
        int[] next = new int[2];
        // следующий ферзь устанавливается через ход конем
        for (int i = 0; i < 8; i++) {
            op++;
            nextX = currX + kMoves[i][1];
            nextY = currY + kMoves[i][0];
            if (isPossible(desk, nextX, nextY) && nextQueen(desk, nextX, nextY, move + 1)) {
                return true;
            }
        }
        // если ходов конем больше нет, проверяется есть ли клетки не находящиеся под ударом
        next = jump(desk);
        nextX = next[0];
        nextY = next[1];
        if (nextX != -1 && nextQueen(desk, nextX, nextY, move + 1)) return true;
        desk[currX][currY] = 0;
        return false;
    }

    private static int[] jump(int[][] desk){
        for (int i = 0; i < desk.length; i++){
            for (int j = 0; j < desk[0].length; j++){
                if (desk[i][j] == 0){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static void printDesk(int[][] desk) {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[0].length; j++) {
                System.out.printf("%3d", desk[i][j]);
            }
            System.out.println();
        }
    }
}
