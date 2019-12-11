package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "level_4s")
@Getter @Setter @ToString(exclude = {"notes", "pictures", "examples"}) @EqualsAndHashCode(of = "id")
public class Level4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(650)")
    private String description;
    @Column
    private Integer access = 5;

    //Czy tutaj potrzebna 2-kierunkowa? Raczej tak dla szybszego chodzenia w górę i w dół drzewa. Trudniejszy update
    @ManyToOne
    private Level3 level3;

    @OneToMany
    private List<Note> notes = new ArrayList<>();

    @OneToMany
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany
    private List<Example> examples = new ArrayList<>();

}