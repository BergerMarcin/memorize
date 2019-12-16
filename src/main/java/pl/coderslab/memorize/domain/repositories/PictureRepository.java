package pl.coderslab.memorize.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.memorize.domain.entities.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByLevelIdOrderByPosNo(Long levelId);

    @Query("SELECT p FROM Picture p WHERE p.level.id = ?1 ORDER BY p.posNo")
    List<Picture> queryNativeFindAllByLevelIdOrderByPosNo(Long levelId);

}
