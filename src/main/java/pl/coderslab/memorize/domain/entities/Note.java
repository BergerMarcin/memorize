package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notes")
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String htmlText;
    @Column(columnDefinition = "VARCHAR(650)")
    private String plainText;
    @Column(nullable = false)
    private Long indexNo;

    //Czy tutaj potrzebna 2-kierunkowa? Raczej tak dla szybszego chodzenia w górę i w dół drzewa. Trudniejszy update
    @ManyToOne
    private Level4 level4;

}