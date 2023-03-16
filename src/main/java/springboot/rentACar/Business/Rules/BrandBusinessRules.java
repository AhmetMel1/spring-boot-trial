package springboot.rentACar.Business.Rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.rentACar.DataAccess.Abstracts.BrandDao;
import springboot.rentACar.Core.Utilities.Exceptions.BusinessException;

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
