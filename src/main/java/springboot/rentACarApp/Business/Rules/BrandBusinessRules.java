package springboot.rentACarApp.Business.Rules;

import springboot.rentACarApp.Core.Exceptions.BusinessException;
import springboot.rentACarApp.DataAccess.Abstracts.BrandDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private BrandDao brandDao;
    public void checkIfBrandNameExists(String name)
    {
    if(this.brandDao.existsByName(name)){
        throw new BusinessException("Brand name already exists");
    }
    }
}
