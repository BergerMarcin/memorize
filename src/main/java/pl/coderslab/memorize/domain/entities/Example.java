package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examples")
@Getter @Setter @ToString(exclude = "exampleLines") @EqualsAndHashCode(of = "id")
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(length = 650)
    private String description;

    @Column(nullable = false)
    private Long posNo;

    // Field can not be empty, so: optional = false
    @ManyToOne(optional = false)
    @JoinColumn (name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "example", cascade = {CascadeType.ALL})
    private List<ExampleLines> exampleLines;

}