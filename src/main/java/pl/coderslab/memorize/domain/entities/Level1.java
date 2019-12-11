package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "level_1s")       // Nazwa tablicy gdy mamy liczbę w nazwie????????????????
@Getter @Setter @ToString(exclude = "level2s") @EqualsAndHashCode(of = "id")
public class Level1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(650)")
    private String description;
    @Column
    private Integer access = 5;         // ????????? Szyfrowanie dostępu ?????????????

    @OneToMany
    private List<Level2> level2s = new ArrayList();

}