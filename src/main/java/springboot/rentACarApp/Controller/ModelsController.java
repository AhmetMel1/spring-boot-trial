package springboot.rentACarApp.Controller;

import springboot.rentACarApp.Business.Abstracts.ModelService;
import springboot.rentACarApp.Business.Requests.CreateModelRequest;
import springboot.rentACarApp.Business.Responses.GetAllModelsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;
    @GetMapping()
    public List<GetAllModelsResponse> getAll(){
        return modelService.getAll();
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add( CreateModelRequest createModelRequest){
        this.modelService.add(createModelRequest);
    }
}
