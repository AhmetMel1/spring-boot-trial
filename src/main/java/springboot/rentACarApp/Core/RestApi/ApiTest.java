package springboot.rentACarApp.Core.RestApi;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiTest {
    HttpClient httpClient=HttpClient.newHttpClient();
    public void list() throws Exception{
        HttpRequest listRequest= HttpRequest.newBuilder().uri(new URI("https://6410863a7b24bb91f2200eed.mockapi.io/api/v7/showrooms")).build();
        HttpResponse listResponse=httpClient.send(listRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(listResponse.body());

    }
    public void listById(Object id) throws Exception{
        System.out.println(id);
        HttpRequest listByIdRequest=HttpRequest.newBuilder().uri(new URI("https://6410863a7b24bb91f2200eed.mockapi.io/api/v7/showrooms/"+id)).build();
        HttpResponse listByIdResponse=httpClient.send(listByIdRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(listByIdResponse.body());
    }
    public void add(String jsonRequest) throws Exception{
        HttpRequest addRequest=HttpRequest.newBuilder().uri(new URI("https://6410863a7b24bb91f2200eed.mockapi.io/api/v7/showrooms"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();
        HttpResponse addResponse=httpClient.send(addRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(addResponse.body());
        System.out.println(jsonRequest);
    }
    public  void update(Object id,String jsonRequest) throws Exception{
        HttpRequest updateRequest= HttpRequest.newBuilder().uri(new URI("https://6410863a7b24bb91f2200eed.mockapi.io/api/v7/showrooms/"+id))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();
        HttpResponse updateResponse=httpClient.send(updateRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(updateResponse.body());
        System.out.println(jsonRequest);
    }
    public void delete(Object id) throws Exception{
        HttpRequest deleteRequest= HttpRequest.newBuilder().uri(new URI("https://6410863a7b24bb91f2200eed.mockapi.io/api/v7/showrooms/"+id))
                .DELETE().build();
        HttpResponse deleteResponse=httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(deleteResponse.body());
    }

}
