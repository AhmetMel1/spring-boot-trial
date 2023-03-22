package springboot.rentACarApp.Business.Abstracts;


import springboot.rentACarApp.Business.Requests.CreateModelRequest;
import springboot.rentACarApp.Business.Responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
}
