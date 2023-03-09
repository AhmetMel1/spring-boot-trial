package springboot.rentACar.Business.Concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.Business.Requests.CreateBrandRequest;
import springboot.rentACar.Business.Responses.GetAllBrandsResponse;
import springboot.rentACar.DataAccess.Abstracts.BrandDao;
import springboot.rentACar.Entities.Concretes.Brand;

import java.util.ArrayList;
import java.util.List;
@Service//Business nesnesi olduğunu belirttik.
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    @Autowired
    public BrandManager(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands=brandDao.findAll();
        List<GetAllBrandsResponse> brandsResponses=new ArrayList<GetAllBrandsResponse>();

        for(Brand brand:brands){
            GetAllBrandsResponse responseItem=new GetAllBrandsResponse();
            responseItem.setId(brand.getId());
            responseItem.setName(brand.getName());

            brandsResponses.add(responseItem);

        }
        return brandsResponses;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        Brand brand=new Brand();
        brand.setName(createBrandRequest.getName());
        this.brandDao.save(brand);
    }
}
