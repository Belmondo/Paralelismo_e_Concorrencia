import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SensorDataClient {

    public static void main(String[] args) {
        try {
            // URL do servidor de dados do sensor
            String serverUrl = "https://belmondojr.dev/sensores.php";

            // Requisição GET para obter todos os dados
            System.out.println("GET Request - All Sensor Data:");
            sendGetRequest(serverUrl);

            // Requisição GET para obter dados de um sensor específico
            System.out.println("\nGET Request - Sensor Data for Temperature:");
            sendGetRequest(serverUrl + "?sensor=temperature");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para enviar uma requisição GET
    private static void sendGetRequest(String url) throws Exception {
        URL serverUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

        // Define o método de requisição
        connection.setRequestMethod("GET");

        // Obtém a resposta
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Lê a resposta
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println("Response Body:\n" + response.toString());
        }

        // Fecha a conexão
        connection.disconnect();
    }
}
