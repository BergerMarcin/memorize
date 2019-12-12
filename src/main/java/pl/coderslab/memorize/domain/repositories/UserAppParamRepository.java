package pl.coderslab.memorize.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.domain.entities.UserAppParam;

public interface UserAppParamRepository extends JpaRepository<User, Long> {

    UserAppParam findFirstByUsername(String loginUserName);

}