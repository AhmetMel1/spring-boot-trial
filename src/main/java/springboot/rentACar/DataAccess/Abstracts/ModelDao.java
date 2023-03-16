package springboot.rentACar.DataAccess.Abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.rentACar.Entities.Concretes.Model;
@Repository
public interface ModelDao extends JpaRepository<Model,Integer> {
}
