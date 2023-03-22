package springboot.rentACarApp.DataAccess.Abstracts;

import springboot.rentACarApp.Entities.Concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelDao extends JpaRepository<Model,Integer> {
}
