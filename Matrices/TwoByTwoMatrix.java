package Matrices;

public class TwoByTwoMatrix {
    // I could make a matrix class altogether
    public static double[][] product2x2Matrix(double[][] A, double[][] B) {
        double[][] C = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                C[i][j] = (A[i][0] * B[0][j]) + (A[i][1] * B[1][j]);
        }
        return C;
    }

    public static void main(String[] args) {
        double[][] A = {
                { 1, 0 },
                { 0, 1 } };
        System.out.println("Matrix A: ");
        displayMatrix(A);
        double[][] B = {
                { 5, 4 },
                { 3, 2 } };
        System.out.println("Matrix B: ");
        displayMatrix(B);
        double[][] C = product2x2Matrix(A, B);
        System.out.println("Matrix AxB: ");
        displayMatrix(C);
        System.out.println("New Test");
        displayMatrix(Matrix.matrixProduct(A, B));

    }

    public static void displayMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("]");
        }
    }
}
