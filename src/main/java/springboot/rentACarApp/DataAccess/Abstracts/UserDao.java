package springboot.rentACarApp.DataAccess.Abstracts;

import springboot.rentACarApp.Entities.Concretes.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
