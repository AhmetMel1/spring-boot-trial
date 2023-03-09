package springboot.rentACar.Business.Concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.DataAccess.Abstracts.BrandDao;
import springboot.rentACar.Entities.Concretes.Brand;

import java.util.List;
@Service//Business nesnesi olduğunu belirttik.
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    @Autowired
    public BrandManager(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public List<Brand> getAll() {
        return brandDao.findAll();
    }
}
