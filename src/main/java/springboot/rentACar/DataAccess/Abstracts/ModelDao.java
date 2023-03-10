package springboot.rentACar.DataAccess.Abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rentACar.Entities.Concretes.Model;

public interface ModelDao extends JpaRepository<Model,Integer> {
}
