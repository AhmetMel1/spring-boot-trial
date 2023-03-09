package springboot.rentACar.WebApi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Requests.UpdateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;
import springboot.rentACar.Business.Responses.GetByIdBrandResponse;


import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;
    @GetMapping()
    public List<GetAllBrandsResponse> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdBrandResponse getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @PutMapping()
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest){
    this.brandService.update(updateBrandRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }
}
