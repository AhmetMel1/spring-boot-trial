package springboot.rentACar.Business.Concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.rentACar.Business.Abstracts.ModelService;
import springboot.rentACar.Business.Requests.CreateModelRequest;
import springboot.rentACar.Business.Responses.GetAllModelsResponse;
import springboot.rentACar.DataAccess.Abstracts.ModelDao;
import springboot.rentACar.Entities.Concretes.Model;
import springboot.rentACar.Core.Utilities.Mappers.ModelMapperService;
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
