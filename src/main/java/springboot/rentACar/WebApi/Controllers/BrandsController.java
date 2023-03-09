package springboot.rentACar.WebApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.rentACar.Business.Abstracts.BrandService;
import springboot.rentACar.Entities.Concretes.Brand;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private BrandService brandService;
    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService=brandService;
    }
    @GetMapping("/getall")
    public List<Brand> getAll(){
        return brandService.getAll();
    }
}
