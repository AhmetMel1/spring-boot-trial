package springboot.rentACar.Business.Abstracts;

import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Requests.CreateModelRequest;
import springboot.rentACar.Business.Responses.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
}
