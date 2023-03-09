package springboot.rentACar.Business.Concretes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;
import springboot.rentACar.DataAccess.Abstracts.BrandDao;
import springboot.rentACar.Entities.Concretes.Brand;
import springboot.rentACar.core.utilities.mappers.ModelMapperService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service//Business nesnesi olduğunu belirttik.
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    /*@Autowired
     BrandManager(BrandDao brandDao) {
        this.brandDao = brandDao;
    }*/

    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands=brandDao.findAll();
        List<GetAllBrandsResponse> brandsResponses=brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());

        return brandsResponses;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        Brand brand=this.modelMapperService.forRequest()
                .map(createBrandRequest,Brand.class);

        this.brandDao.save(brand);
    }
}
