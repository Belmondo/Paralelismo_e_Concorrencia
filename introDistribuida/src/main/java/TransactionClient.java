import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TransactionClient {

    public static void main(String[] args) {
        try {
            // URL do servidor de transações
                String serverUrl = "https://belmondojr.dev/compra.php?";

            // Dados da transação
            List<String> products = new ArrayList<>();
            List<String> amounts = new ArrayList<>();
            products.add("Item1");
            products.add("Item2");
            amounts.add("10.50");
            amounts.add("20.75");

            // Envia a transação para o servidor e recebe a soma total
            double totalAmount = sendTransaction(serverUrl, products, amounts);

            // Exibe a soma total recebida do servidor
            System.out.println("Soma total dos valores recebidos do servidor: " + totalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para enviar a transação para o servidor e receber a soma total
    private static double sendTransaction(String url, List<String> products, List<String> amounts) throws Exception {
        // Constrói a URL com os parâmetros
        StringBuilder urlWithParams = new StringBuilder(url);
        for (int i = 0; i < products.size(); i++) {
            urlWithParams.append("&products[]=").append(URLEncoder.encode(products.get(i), "UTF-8"));
            urlWithParams.append("&amounts[]=").append(amounts.get(i));
        }

        // Cria a conexão
        URL serverUrl = new URL(urlWithParams.toString());
        System.out.println(urlWithParams.toString());
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

        // Define o método de requisição
        connection.setRequestMethod("GET");

        // Obtém a resposta
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Lê a resposta
        double totalAmount = 0.0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Exibe a resposta do servidor
            System.out.println("Response Body:\n" + response.toString());

            // Obtém a soma total dos valores recebidos do servidor
            totalAmount = Double.parseDouble(response.toString());
        }

        // Fecha a conexão
        connection.disconnect();

        return totalAmount;
    }
}
