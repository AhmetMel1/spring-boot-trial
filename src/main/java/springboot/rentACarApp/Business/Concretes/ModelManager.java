package springboot.rentACarApp.Business.Concretes;

import springboot.rentACarApp.Business.Abstracts.ModelService;
import springboot.rentACarApp.Business.Requests.CreateModelRequest;
import springboot.rentACarApp.Business.Responses.GetAllModelsResponse;
import springboot.rentACarApp.Core.Mappers.ModelMapperService;
import springboot.rentACarApp.DataAccess.Abstracts.ModelDao;
import springboot.rentACarApp.Entities.Concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelDao modelDao;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models=modelDao.findAll();//Veri tabanındaki tüm verileri brand nesnesinin içine atadı
        List<GetAllModelsResponse> modelsResponses=models.stream()
                .map(model -> this.modelMapperService.forResponse()
                        .map(model,GetAllModelsResponse.class)).collect(Collectors.toList());//Verileri aktardı.

        return modelsResponses;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
    Model model=this.modelMapperService.forRequest()
            .map(createModelRequest,Model.class);
    this.modelDao.save(model);
    }
}
