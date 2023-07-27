package controlador;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConeccionGPT {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void setApiKey(String newApiKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private final String apiKey;

    public ConeccionGPT(String apiKey) {
        this.apiKey = "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";
    }

    public String obtenerRespuestaChatGPT(String userMessage) {
        try {
            // Construye la solicitud HTTP
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Construye el cuerpo de la solicitud (el mensaje del usuario)
            String requestBody = "{\"model\":"
                    + " \"gpt-3.5-turbo\","
                    + " \"messages\": "
                    + "[{\"role\": "
                    + "\"user\","
                    + " \"content\":"
                    + " \"" + userMessage + "\"}]}";

            // Envía la solicitud
            connection.setDoOutput(true);
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(requestBody);
                outputStream.flush();
            }

            StringBuffer response;
            try ( // Lee la respuesta de la API
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Extrae y retorna la respuesta de la API
            return response.toString();

        } catch (IOException e) {
            return "Error al obtener la respuesta de ChatGPT.";
        }
    }
}

 