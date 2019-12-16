package pl.coderslab.memorize.domain.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.memorize.domain.entities.Level;
import pl.coderslab.memorize.domain.entities.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByLevelIdOrderByPosNo (Long levelId);

    @Query("SELECT n FROM Note n WHERE n.level.id = ?1 ORDER BY n.posNo")
    List<Note> queryNativeFindAllByLevelIdOrderByPosNo (Long levelId);

}
