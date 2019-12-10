package pl.coderslab.memorize.domain.repositories;

import pl.coderslab.memorize.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);
}
