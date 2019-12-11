package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition = "VARCHAR(650)")
    private String description;
    @Column(nullable = false)
    private Long indexNo;
    @Column
    private String path;
    @Column
    private String filename;
    @Column
    private String fileType;
    @Column (columnDefinition = "MEDIUMBLOB")
    private byte[] fileData; //???????????????? MEDIUMBLOB to byte[] czy String do zdjęć?

    //Czy tutaj potrzebna 2-kierunkowa? Raczej tak dla szybszego chodzenia w górę i w dół drzewa. Trudniejszy update
    @ManyToOne
    private Level4 level4;

}