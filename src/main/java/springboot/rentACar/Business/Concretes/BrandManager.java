package springboot.rentACar.Business.Concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Requests.UpdateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;
import springboot.rentACar.Business.Responses.GetByIdBrandResponse;
import springboot.rentACar.Business.Rules.BrandBusinessRules;
import springboot.rentACar.DataAccess.Abstracts.BrandDao;
import springboot.rentACar.Entities.Concretes.Brand;
import springboot.rentACar.Core.Utilities.Mappers.ModelMapperService;
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
