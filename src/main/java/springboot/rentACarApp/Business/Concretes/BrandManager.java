package springboot.rentACarApp.Business.Concretes;

import springboot.rentACarApp.Business.Abstracts.BrandService;
import springboot.rentACarApp.Business.Requests.CreateBrandRequest;
import springboot.rentACarApp.Business.Requests.UpdateBrandRequest;
import springboot.rentACarApp.Business.Responses.GetAllBrandsResponse;
import springboot.rentACarApp.Business.Responses.GetByIdBrandResponse;
import springboot.rentACarApp.Business.Rules.BrandBusinessRules;
import springboot.rentACarApp.Core.Mappers.ModelMapperService;
import springboot.rentACarApp.DataAccess.Abstracts.BrandDao;
import springboot.rentACarApp.Entities.Concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service//Business nesnesi olduğunu belirttik.
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;
    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands=brandDao.findAll();//Veri tabanındaki tüm verileri brand nesnesinin içine atadı
        List<GetAllBrandsResponse> brandsResponses=brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());//Verileri aktardı.

        return brandsResponses;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        Brand brand=this.brandDao.findById(id).orElseThrow();//Verileri id'ye göre getirdi.

        GetByIdBrandResponse response=this.modelMapperService.forResponse().map(brand,GetByIdBrandResponse.class);

        return response;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());

        Brand brand=this.modelMapperService.forRequest()
                .map(createBrandRequest,Brand.class);

        this.brandDao.save(brand);
    }
    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand=this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);

        this.brandDao.save(brand);

    }
    @Override
    public void delete(int id) {
        this.brandDao.deleteById(id);
    }
}
