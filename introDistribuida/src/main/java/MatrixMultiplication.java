import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MatrixMultiplication {
    public static void main(String[] args) {
        
    }

    // Converte uma matriz em formato JSON
    public static String convertMatrixToJson(int[][] matrix) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < matrix.length; i++) {
            json.append("[");
            for (int j = 0; j < matrix[i].length; j++) {
                json.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            if (i < matrix.length - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
