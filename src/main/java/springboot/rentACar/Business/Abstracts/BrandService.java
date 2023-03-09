package springboot.rentACar.Business.Abstracts;

import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Requests.UpdateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;
import springboot.rentACar.Business.Responses.GetByIdBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);

}
