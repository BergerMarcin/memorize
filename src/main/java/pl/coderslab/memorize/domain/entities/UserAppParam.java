package pl.coderslab.memorize.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Parameters for web application of settings to be stored:
 *  - actual data-levels chosen by the user
 *  - message to be confirm
 *  - any other if needed
 * That data will be stored in session as a object as a field "webAppParam" (see class HomePageController)
 */
@Entity
@Table(name = "user_app_params")
@Data
public class UserAppParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn (name = "owner_id")
    private User owner;
    // Actual data-level collection to be viewed for certain user
    // That collection is in fact applied by Hibernate as next MySQL table (but we do not need it as only 1 functional field)
    // Name of that table was added to have possibility to use JOIN with that table_name at SELECT
    @ElementCollection
    @CollectionTable(name = "user_view_levels_id")
    private List<Long> viewLevelIds;
    @Column
    private Long posNoLevel;
    @Column
    private Long posNoExample;
    @Column
    private String alertMessage;

}
