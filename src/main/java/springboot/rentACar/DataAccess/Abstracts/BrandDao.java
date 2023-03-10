package springboot.rentACar.DataAccess.Abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.rentACar.Entities.Concretes.Brand;

import java.util.Optional;


@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
    Optional<Brand> findAllById(Integer integer);
    // select * from brand where name like '';

    boolean existsByName(String name);//Spring JPA özel keyword

}
