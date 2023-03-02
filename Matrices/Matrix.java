package Matrices;

public class Matrix {
    public static double[][] matrixProduct(double[][] A, double[][] B) {
        // the size of the product matrix is always m x n
        int m = A[0].length; // columns
        int n = B.length; // rows
        if (m != n) {
            System.out.println("Invalid Matrix Sizes : The matrices cannot be multiplied");
            return null;
        }

        // The actual operation
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                C[i][j] = (A[i][0] * B[0][j]) + (A[i][1] * B[1][j]);
        }
        return C;
    }
}
