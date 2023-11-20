import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class SortingClient {

    public static void main(String[] args) {
        try {
            // URL do servidor de ordenação
            String serverUrl = "https://belmondojr.dev/ordenacao.php";

            // Método de ordenação e vetor de dados
            String method = "bubbleSort";
            String[] vector = {"5", "2", "8", "1"};

            // Constrói a URL com os parâmetros
            String urlWithParams = serverUrl + "?method=" + method;
            for (String value : vector) {
                urlWithParams += "&vector[]=" + value;
            }

            // Cria a conexão
            URL url = new URL(urlWithParams);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Define o método de requisição
            connection.setRequestMethod("GET");

            // Obtém a resposta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lê a resposta
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Exibe a resposta
                System.out.println(response.toString());
                in.close();
            } else {
                System.out.println("Falha na requisição. Código de resposta: " + responseCode);
            }

            // Fecha a conexão
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
