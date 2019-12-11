package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "example_lines")
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class ExampleLines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String htmlLine;
    @Column(columnDefinition = "VARCHAR(650)")
    private String plainLine;
    @Column(columnDefinition = "VARCHAR(325)")
    private String plainDescr;
    @Column(columnDefinition = "VARCHAR(500)")
    private String htmlDescr;
    @Column(nullable = false)
    private Long indexNo;

    //Czy tutaj potrzebna 2-kierunkowa? Raczej tak dla szybszego chodzenia w górę i w dół drzewa. Trudniejszy update
    @ManyToOne
    private Example example;
}