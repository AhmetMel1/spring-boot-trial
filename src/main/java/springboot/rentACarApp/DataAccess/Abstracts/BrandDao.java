package springboot.rentACarApp.DataAccess.Abstracts;

import springboot.rentACarApp.Entities.Concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
        Optional<Brand> findAllById(Integer integer);
    // select * from brand where name like '';

    boolean existsByName(String name);//Spring JPA özel keyword

}
