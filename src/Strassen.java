import javax.swing.*;
import java.util.Scanner;

import java.util.*;

public class Strassen {

    public int[][] matrixmult(int[][]a, int[][]b)
    {

        /* a행렬, b행렬 생성 */
        int i;
        int j;
        int k;

        int n = a.length;
        int c[][] = new int[n][n];  //a행렬과 b행렬의 곱을 저장할 다른 n*n행렬인 c행렬

        //두 행렬의 곱셈 계산과 출력
        for(i =0;i<a.length;i++)

        {
            for (j = 0; j < a.length; j++) {
                c[i][j] = 0;

                for (k = 0; k < a.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
                System.out.print(c[i][j] + "");
            }
            System.out.println();
        }

        if(n ==1)
            c[0][0] = a[0][0] * b[0][0];
        else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            divide(a, A11, 0, 0);
            divide(a, A12, 0, n / 2);
            divide(a, A21, n / 2, 0);
            divide(a, A22, n / 2, n / 2);

            divide(b, B11, 0, 0);
            divide(b, B12, 0, n / 2);
            divide(b, B21, n / 2, 0);
            divide(b, B22, n / 2, n / 2);

            int [][] M1 = matrixmult(add(A11, A22), add(B11, B22));
            int [][] M2 = matrixmult(add(A21, A22), B11);
            int [][] M3 = matrixmult(A11, sub(B12, B22));
            int [][] M4 = matrixmult(A22, sub(B21, B11));
            int [][] M5 = matrixmult(add(A11, A12), B22);
            int [][] M6 = matrixmult(sub(A21, A11), add(B11, B12));
            int [][] M7 = matrixmult(sub(A12, A22), add(B21, B22));

            int [][] C11 = add(sub(add(M1, M4), M5), M7);
            int [][] C12 = add(M3, M5);
            int [][] C21 = add(M2, M4);
            int [][] C22 = add(sub(add(M1, M3), M2), M6);

            com(C11, c, 0, 0);
            com(C12, c, 0, n / 2);
            com(C21, c, n / 2, 0);
            com(C22, c, n / 2, n / 2);
        }
        return c;
    }


    public void com(int[][] c, int[][] d, int ib, int jb)
    {
        for(int i1 = 0, i2 = ib; i1 < c.length; i1++, i2++)
            for(int j1 = 0, j2 = jb; j1 < c.length; j1++, j2++)
                d[i2][j2] = c[i1][j1];
    }

    public static int[][] add(int[][] a, int[][] b)
    {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    public static int[][] sub(int[][] a, int[][] b)
    {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    public void divide(int[][] c, int[][] d, int ib, int jb)
    {
        for(int i1 = 0, i2 = ib; i1 < c.length; i1++, i2++)
            for(int j1 = 0, j2 = jb; j1 < c.length; j1++, j2++)
                c[i1][j1] = d[i2][j2];
    }

    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);

        System.out.println("n*n행렬의 n값을 입력하시오");
        int n = sc.nextInt();
        System.out.println("첫번째 행렬을 입력하시오");
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = sc.nextInt();

        System.out.println("두번째 행렬을 입력하시오");
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                b[i][j] = sc.nextInt();

        Strassen S = new Strassen();
        int[][] c = S.matrixmult(a, b);

        System.out.println("n*n행렬:");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                System.out.print(c[i][j] +" ");
            System.out.println();
        }


    }
}
