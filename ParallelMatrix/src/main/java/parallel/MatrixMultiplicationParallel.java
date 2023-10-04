package parallel;

import java.util.Date;
import util.MatrixGeneratorUtil;

/**
 * Normal way to do matrix multiplication.
 *
 * @author JavaProgramTo.com
 *
 */
public class MatrixMultiplicationParallel {

    public static void startParallel() {

        Date start = new Date();

        int[][] m1 = MatrixGeneratorUtil.generateMatrix(2042, 2042);
        int[][] m2 = MatrixGeneratorUtil.generateMatrix(2042, 2042);

        System.out.println("Matrix 1 : ");
        MatrixGeneratorUtil.print(m1);

        System.out.println("\nMatrix 2 : ");
        MatrixGeneratorUtil.print(m2);

        System.out.println("\nOutput Matrix : ");
        int[][] result = new int[m1.length][m2[0].length];
        ParallelThreadsCreator.multiply(m1, m2, result);
        MatrixGeneratorUtil.print(result);

        Date end = new Date();
        System.out.println("\nTempo Paralelo em  milissegundos: " + (end.getTime() - start.getTime()));

    }

}
