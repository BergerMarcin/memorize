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
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(length = 650)
    private String description;
    @Column(nullable = false)
    private Long indexNo;

    //Czy tutaj potrzebna 2-kierunkowa? Raczej tak dla szybszego chodzenia w górę i w dół drzewa. Trudniejszy update
    @ManyToOne
    private Level4 level4;

    @OneToMany
    private List<ExampleLines> exampleLines = new ArrayList<>();

}