package pl.coderslab.memorize.domain.repositories;

import pl.coderslab.memorize.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
