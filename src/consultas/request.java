package consultas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class request {

    private  static final String API_KEY = "0aab93b56240deadd848a639";
    public String obtenerconversiones() throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/0aab93b56240deadd848a639/latest/USD"))
                .header("Autorization", API_KEY)
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());
        return response.body();
    }
}




