package pl.coderslab.memorize.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.memorize.domain.entities.Example;
import pl.coderslab.memorize.domain.entities.Example;

import java.util.List;

public interface ExampleRepository extends JpaRepository<Example, Long> {

    List<Example> findAllByLevelIdOrderByPosNo(Long levelId);

    @Query("SELECT e FROM Example e WHERE e.level.id = ?1 ORDER BY e.posNo")
    List<Example> queryNativeFindAllByLevelIdOrderByPosNo(Long levelId);

}
