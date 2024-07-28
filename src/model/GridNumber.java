package model;

import java.util.Arrays;
import java.util.Random;

public class GridNumber {
    private int X_COUNT;
    private int Y_COUNT;

    private int[][] numbers;
    private int[][][] place;
    private int[][][] place2;
    private int[][] big;
    static Random random = new Random();

    public GridNumber(int size) {
        X_COUNT = size;
        Y_COUNT = size;
        this.numbers = new int[size][size];
        this.place = new int[size][size][2];
        this.place2 = new int[size][size][2];
        this.big = new int[size][size];
        this.initialNumbers();
    }
    public int[][] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }
    public void initialNumbers() {
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                numbers[i][j] = 0;
            }
        }
        int x1 = random.nextInt(X_COUNT);
        int y1 = random.nextInt(Y_COUNT);
        int x2 = random.nextInt(X_COUNT);
        int y2 = random.nextInt(Y_COUNT);
        while (x1 == x2 && y1 == y2) {
            x2 = random.nextInt(X_COUNT);
            y2 = random.nextInt(Y_COUNT);
        }
        numbers[x1][y1] = 2;
        numbers[x2][y2] = 4;
    }
    public void setBarrier(){
        int xr = random.nextInt(X_COUNT);
        int yr = random.nextInt(Y_COUNT);
        while (numbers[xr][yr] != 0) {
            xr = random.nextInt(X_COUNT);
            yr = random.nextInt(Y_COUNT);
        }
        numbers[xr][yr] = 1;
    }
    public boolean moveRight() {
        big = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                for (int k = 0; k < 2; k++) {
                    place[i][j][k] = -1;
                    place2[i][j][k] = -1;
                }
            }
        }
        boolean ifMove = false;
        int n = Y_COUNT - 1;
        int[] zero = new int[X_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = Y_COUNT - 1; j >= 0; j--) {
                if (numbers[i][j] != 0) {
                    numbers[i][n] = numbers[i][j];
                    place[i][j][0] = i;
                    place[i][j][1] = n;
                    place2[i][j][0] = i;
                    place2[i][j][1] = n;
                    if(n!=j){
                        ifMove = true;
                    }
                    n--;
                }
            }
            zero[i] = n + 1;
            for (int k = 0; k <= n; k++) {
                numbers[i][k] = 0;
            }
            n = Y_COUNT - 1;
        }
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = Y_COUNT - 1; j > 0; j--) {
                if (numbers[i][j] == numbers[i][j - 1] && numbers[i][j] !=0) {
                    numbers[i][j] = numbers[i][j] + numbers[i][j - 1];
                    int x = getOrigin2(i,j)[0];
                    int y = getOrigin2(i,j)[1];
                    big[x][y] = 1;
                    numbers[i][j - 1] = 0;
                    for (int k = zero[i]; k < j; k++) {
                        place[this.getOrigin2(i,k)[0]][this.getOrigin2(i,k)[1]][1]++;
                    }
                    ifMove = true;
                }
            }
        }
        n = Y_COUNT - 1;
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = Y_COUNT - 1; j >= 0; j--) {
                if (numbers[i][j] != 0) {
                    numbers[i][n] = numbers[i][j];
                    n--;
                }
            }
            for (int k = 0; k <= n; k++) {
                numbers[i][k] = 0;
            }
            n = Y_COUNT - 1;
        }
        return ifMove;

    }


    public boolean moveLeft() {
        big = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                for (int k = 0; k < 2; k++) {
                    place[i][j][k] = -1;
                    place2[i][j][k] = -1;
                }
            }
        }
        boolean ifMove = false;
        int n = 0;
        int[] zero = new int[X_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (numbers[i][j] != 0) {
                    numbers[i][n] = numbers[i][j];
                    place[i][j][0] = i;
                    place[i][j][1] = n;
                    place2[i][j][0] = i;
                    place2[i][j][1] = n;
                    if(n!=j){
                        ifMove = true;
                    }
                    n++;
                }
            }
            zero[i] = n-1;
            for (int k = Y_COUNT - 1; k >= n; k--) {
                numbers[i][k] = 0;
            }
            n = 0;
        }
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT - 1; j++) {
                if (numbers[i][j] == numbers[i][j + 1]&&numbers[i][j]!=0) {
                    numbers[i][j] = numbers[i][j] + numbers[i][j + 1];
                    numbers[i][j + 1] = 0;
                    int x = getOrigin2(i,j)[0];
                    int y = getOrigin2(i,j)[1];
                    big[x][y] = 1;
                    for (int k = j+1; k <= zero[i]; k++) {
                        place[this.getOrigin2(i,k)[0]][this.getOrigin2(i,k)[1]][1]--;
                    }
                    ifMove = true;
                }
            }
        }
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (numbers[i][j] != 0) {
                    numbers[i][n] = numbers[i][j];
                    n++;
                }

            }
            for (int k = Y_COUNT - 1; k >= n; k--) {
                numbers[i][k] = 0;
            }
            n = 0;
        }
        return ifMove;
    }



    public boolean moveUp() {
        big = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                for (int k = 0; k < 2; k++) {
                    place[i][j][k] = -1;
                    place2[i][j][k] = -1;
                }
            }
        }
        boolean ifMove = false;
        int n = 0;
        int[] zero = new int[Y_COUNT];
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = 0; j < X_COUNT; j++) {
                if (numbers[j][i] != 0) {
                    numbers[n][i] = numbers[j][i];
                    place[j][i][0] = n;
                    place[j][i][1] = i;
                    place2[j][i][0] = n;
                    place2[j][i][1] = i;
                    if(n!=j){
                        ifMove = true;
                    }
                    n++;
                }
            }
            zero[i] = n - 1;
            for (int k = n; k < X_COUNT; k++) {
                numbers[k][i] = 0;
            }
            n = 0;
        }
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = 0; j < X_COUNT - 1; j++) {
                if (numbers[j][i] == numbers[j + 1][i] && numbers[j][i] !=0) {
                    numbers[j][i] = numbers[j][i] * 2;
                    numbers[j + 1][i] = 0;
                    int x = getOrigin2(j,i)[0];
                    int y = getOrigin2(j,i)[1];
                    big[x][y] = 1;
                    for (int k = j+1; k <= zero[i]; k++) {
                        place[this.getOrigin2(k,i)[0]][this.getOrigin2(k,i)[1]][0]--;
                    }
                    ifMove = true;
                }
            }
        }
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = 0; j < X_COUNT; j++) {
                if (numbers[j][i] != 0) {
                    numbers[n][i] = numbers[j][i];
                    n++;
                }
            }
            for (int k = n; k < X_COUNT; k++) {
                numbers[k][i] = 0;
            }
            n = 0;
        }
        return ifMove;
    }



    public boolean moveDown() {
        big = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                for (int k = 0; k < 2; k++) {
                    place[i][j][k] = -1;
                    place2[i][j][k] = -1;
                }
            }
        }
        boolean ifMove = false;
        int n = X_COUNT - 1;
        int[] zero = new int[Y_COUNT];
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = X_COUNT - 1; j >= 0; j--) {
                if (numbers[j][i] != 0) {
                    numbers[n][i] = numbers[j][i];
                    place[j][i][0] = n;
                    place[j][i][1] = i;
                    place2[j][i][0] = n;
                    place2[j][i][1] = i;
                    if(n!=j){
                        ifMove = true;
                    }
                    n--;
                }
            }
            zero[i] = n + 1;
            for (int k = 0; k <= n; k++) {
                numbers[k][i] = 0;
            }
            n = X_COUNT - 1;
        }
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = X_COUNT - 1; j > 0; j--) {
                if (numbers[j][i] == numbers[j - 1][i]&&numbers[j][i]!=0) {
                    numbers[j][i] = numbers[j][i] * 2;
                    numbers[j - 1][i] = 0;
                    int x = getOrigin2(j,i)[0];
                    int y = getOrigin2(j,i)[1];
                    big[x][y] = 1;
                    for (int k = zero[i]; k <= j-1; k++) {
                        place[this.getOrigin2(k,i)[0]][this.getOrigin2(k,i)[1]][0]++;
                    }
                    ifMove=true;
                }
            }
        }
        n = X_COUNT - 1;
        for (int i = 0; i < Y_COUNT; i++) {
            for (int j = X_COUNT - 1; j >= 0; j--) {
                if (numbers[j][i] != 0) {
                    numbers[n][i] = numbers[j][i];
                    n--;
                }
            }
            for (int k = 0; k <= n; k++) {
                numbers[k][i] = 0;
            }
            n = X_COUNT - 1;
        }
        return ifMove;
    }

    public int[] addSticker() {
        int xr = random.nextInt(X_COUNT);
        int yr = random.nextInt(Y_COUNT);
        while (numbers[xr][yr] != 0) {
            xr = random.nextInt(X_COUNT);
            yr = random.nextInt(Y_COUNT);
        }
        numbers[xr][yr] = random.nextInt(2) == 0 ? 2 : 4;
        printNumber();
        int[] r = new int[2];
        r[0] = xr;
        r[1] = yr;
        return r;
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }

    public boolean ifEmpty() {
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (numbers[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public boolean ifLR() {
        if (ifEmpty())
            return true;
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT - 1; j++) {
                if (numbers[i][j] == numbers[i][j + 1])
                    return true;
            }
        }
        return false;
    }

    public boolean ifUD() {
        if (ifEmpty())
            return true;
        for (int i = 0; i < X_COUNT - 1; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (numbers[i][j] == numbers[i + 1][j])
                    return true;
            }
        }
        return false;
    }
    public int[] getOrigin(int x,int y){
        int[] r = new int[2];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (this.place[i][j][0] == x && this.place[i][j][1] == y){
                    r[0] = i;
                    r[1] = j;
                }
            }
        }
        return r;
    }
    public int[] getOrigin2(int x,int y){
        int[] r = new int[2];
        for (int i = 0; i < X_COUNT; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (this.place2[i][j][0] == x && this.place2[i][j][1] == y){
                    r[0] = i;
                    r[1] = j;
                }
            }
        }
        return r;
    }
    public int[][][] getPlace(){return place;}
    public int[][] getBig(){
        return big;
    }
    public void burn(int row){
        for(int i= 0; i < Y_COUNT;i++){
//            if (numbers[row][i]!=1)
            numbers[row][i]= 0;
        }
    }


}