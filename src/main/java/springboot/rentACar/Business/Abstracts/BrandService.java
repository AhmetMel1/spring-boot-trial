package springboot.rentACar.Business.Abstracts;

import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    void add(CreateBrandRequest createBrandRequest);
}
