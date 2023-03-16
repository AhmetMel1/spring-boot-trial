package springboot.rentACar.DataAccess.Abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.rentACar.Entities.Concretes.User.User;

import java.util.Optional;
public interface UserDao extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
