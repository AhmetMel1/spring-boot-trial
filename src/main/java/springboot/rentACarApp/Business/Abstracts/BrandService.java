package springboot.rentACarApp.Business.Abstracts;



import springboot.rentACarApp.Business.Requests.CreateBrandRequest;
import springboot.rentACarApp.Business.Requests.UpdateBrandRequest;
import springboot.rentACarApp.Business.Responses.GetAllBrandsResponse;
import springboot.rentACarApp.Business.Responses.GetByIdBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetByIdBrandResponse getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);

}
