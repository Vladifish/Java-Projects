package Matrices;

public class TwoByTwoMatrix {
    // I could make a matrix class altogether
    double[][] product2x2Matrix(double[][] A, double[][] B) {
        double[][] C = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                C[i][j] = (A[i][0] * B[0][j]) + (A[i][1] * B[1][j]);
        }
        return C;
    }
}
