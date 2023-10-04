package serial;

import util.MatrixGeneratorUtil;

import java.util.Date;

public class SerialMultiplication {
    public static void startSerial() {

        Date startTime = new Date();

        int[][] m1 = MatrixGeneratorUtil.generateMatrix(2042, 2042);
        int[][] m2 = MatrixGeneratorUtil.generateMatrix(2042, 2042);

        int[][] result = multiply(m1, m2);

        System.out.println("Matrix 1 : ");
        MatrixGeneratorUtil.print(m1);

        System.out.println("\nMatrix 2 : ");
        MatrixGeneratorUtil.print(m2);

        System.out.println("\nOutput Matrix : ");
        MatrixGeneratorUtil.print(result);

        Date endTime = new Date();
        System.out.println("\nTempo Serial em  milissegundos: " + (endTime.getTime() - startTime.getTime()));

    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int resultRows = matrix1.length;
        int resultColumns = matrix2[0].length;

        int[][] result = new int[resultRows][resultColumns];

        int columns2 = matrix2[0].length;

        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < columns2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < resultColumns; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;

    }
}
