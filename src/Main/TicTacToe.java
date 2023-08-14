package Main;

public class TicTacToe {
    static final int X = 1, O = 2;
    static final int NUM_COLUMNS = 3;
    static final int NUM_IN_ROW = 3;
    static int p1, p2, p3 = 0;
    static long cnt = 0;

    public static void main(String[] args) {
        for (int k = 0; k < 5; k++) {
            int[][] list = new int[NUM_COLUMNS][NUM_COLUMNS];
            p1 = 0; p2 = 0; p3 = 0; cnt = 0;

            switch (k) {
                case 0: Play(list, X); break;
                case 1: list[0][0] = X; Play(list, O); break;
                case 2: list[0][1] = X; Play(list, O); break;
                case 3: list[1][1] = X; Play(list, O); break;
                case 4: list[2][0] = X; Play(list, O); break;
            }

            System.out.println("X-wins: " + p1 + " O-Wins:" + p2 + " Draws:" + p3 + "  Recursion calls: " + cnt);
        }
    }

    public static void Play(int[][] inlist, int inplayer) {
        int res = checkBoard(inlist, 3 - inplayer);
        if (res < 3) {
            if (res == 0) {
                p3++;
                return;
            } else if (res == X) {
                p1++;
                return;
            } else {
                p2++;
                return;
            }
        }

        cnt++;

        for (int row = 0; row < NUM_COLUMNS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (inlist[row][col] == 0) {
                    int[][] clonelist = cloneList(inlist);
                    clonelist[row][col] = inplayer;

                    if (checkBoard(clonelist, inplayer) == inplayer) {
                        Play(clonelist, 3 - inplayer);
                        return;
                    }

                    clonelist[row][col] = 3 - inplayer;
                    if (checkBoard(clonelist, 3 - inplayer) == (3 - inplayer)) {
                        clonelist[row][col] = inplayer;
                        Play(clonelist, 3 - inplayer);
                        return;
                    }
                }
            }
        }

        for (int row = 0; row < NUM_COLUMNS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (inlist[row][col] == 0) {
                    int[][] clonelist = cloneList(inlist);
                    clonelist[row][col] = inplayer;
                    Play(clonelist, 3 - inplayer);
                }
            }
        }
    }

    public static int[][] cloneList(int[][] inlist) {
        int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
        for (int x = 0; x < NUM_COLUMNS; x++) {
            for (int y = 0; y < NUM_COLUMNS; y++) {
                clonelist[x][y] = inlist[x][y];
            }
        }
        return clonelist;
    }

    public static boolean isFull(int[][] inlist) {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                if (inlist[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int checkBoard(int[][] inlist, int player) {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            if (inlist[i][0] == player && inlist[i][1] == player && inlist[i][2] == player) return player;
            if (inlist[0][i] == player && inlist[1][i] == player && inlist[2][i] == player) return player;
        }
        if (inlist[0][0] == player && inlist[1][1] == player && inlist[2][2] == player) return player;
        if (inlist[0][2] == player && inlist[1][1] == player && inlist[2][0] == player) return player;

        return isFull(inlist) ? 0 : 3;
    }

    public static void printlist(int[][] inlist) {
        for (int i = 0; i < inlist.length; i++) {
            for (int j = 0; j < inlist[i].length; j++) {
                System.out.print(inlist[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
