package ru.konsist.services.httpConnect;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpClientService {
    public void requestMessageHttp(){
        Map<Object, Object> data = new HashMap<>();
        data.put("fistname", "admin");
        data.put("lastname", "admin");
        data.put("age", 25);

        HttpClient client = HttpClient.newHttpClient();

//        HttpRequest request = HttpRequest.newBuilder()
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .uri(URI.create("https://httpbin.org/post"))
//                .POST(ofForm(data))
//                .build();
//
//        HttpResponse<String> response = null;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("Status code: " + response.statusCode());
//        System.out.println("\n Body: " + response.body());
//    }

    }
}
