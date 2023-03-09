package springboot.rentACar.Business.Abstracts;

import springboot.rentACar.Entities.Concretes.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
}
