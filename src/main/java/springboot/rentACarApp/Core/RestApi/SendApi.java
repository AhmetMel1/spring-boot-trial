package springboot.rentACarApp.Core.RestApi;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SendApi {
    Gson gson=new Gson();
    Transcript transcript=new Transcript();
    HttpClient httpClient = HttpClient.newHttpClient();
    public void post(String audio_url) throws Exception
    {
        transcript.setAudio_url(audio_url);

        String jsonRequest = gson.toJson(transcript);

        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "75fabda8954d4fa6bb67828b3a329416")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        transcript = gson.fromJson(postResponse.body(), Transcript.class);
    }
    public void get(String id) throws Exception
    {
        HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript/" + id))
                .header("Authorization", "75fabda8954d4fa6bb67828b3a329416").build();
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println("Status:" + transcript.getStatus());
            if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())) {
                break;
            }
            Thread.sleep(1000);
        }
        System.out.println("Transcription completed!");
        System.out.println(transcript.getText());
    }
    public void delete(String id) throws Exception
    {
        HttpRequest deleteRequest= HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript/" + id))
                .header("Authorization", "75fabda8954d4fa6bb67828b3a329416")
                .DELETE().build();

        HttpResponse<String> deleteResponse=httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(deleteResponse.body());
    }
}
