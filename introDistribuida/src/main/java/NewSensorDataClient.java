import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NewSensorDataClient {

    public static void main(String[] args) {
        try {
            // URL do servidor de dados do sensor
            String serverUrl = "https://belmondojr.dev/comunicacao.php";

            // Dados do sensor
            String[] sensors = {"temperature", "humidity", "pressure"};

            // Envia os dados do sensor para o servidor
            sendSensorData(serverUrl, sensors);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para enviar dados do sensor para o servidor
    private static void sendSensorData(String url, String[] sensors) throws Exception {
        // Constrói a URL com os parâmetros
        StringBuilder urlWithParams = new StringBuilder(url);
        for (String sensor : sensors) {
            urlWithParams.append("&sensors[]=").append(URLEncoder.encode(sensor, "UTF-8"));
        }

        // Cria a conexão
        URL serverUrl = new URL(urlWithParams.toString());
        System.out.println(serverUrl);
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

            // Exibe a resposta do servidor
            System.out.println("Response Body:\n" + response.toString());
        }

        // Fecha a conexão
        connection.disconnect();
    }
}
