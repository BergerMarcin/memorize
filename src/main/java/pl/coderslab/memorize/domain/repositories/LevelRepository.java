package pl.coderslab.memorize.domain.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.memorize.domain.entities.Level;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"children"})
    Level findFirstByIdWithChildren(Long id);

    // Shortly: Top / First / Start level
    // Full description:
    //  1. Top level
    //  2. Only top / the 1st level has NULL parent (means top level hierarchy).
    //  3. The top level is the only one item (but below just in a case the first is
    //  4. List are not read/loaded except list children (because all lists are loaded LAZY and because no @EntityGraph(type =
    //     EntityGraph.EntityGraphType.LOAD, attributePaths = {"children", "notes", "pictures", "examples"}))
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"children"})
    Level findFirstByParentIsNullOrderByPosNoWithChildren();

    // Shortly: First position of sublevel/children of passed/send level id (parent level). Any lists is taken
    // Full-precision description:
    //  1. The first (acc. position number) children level of the parent level of id
    //  2. List are not read/loaded except list children (because all lists are loaded LAZY and because no @EntityGraph(type =
    //     EntityGraph.EntityGraphType.LOAD, attributePaths = {"children", "notes", "pictures", "examples"}))
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"children"})
    Level findFirstByParent_IdOrderByPosNoWithChildren(Long parentId);


}
