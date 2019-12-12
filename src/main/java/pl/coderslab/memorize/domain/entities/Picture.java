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
    @Column
    private String filename;
    @Column
    private String contentType;
    @Lob
    @Column (columnDefinition = "MEDIUMBLOB")
    private byte[] fileData; // MEDIUMBLOB is byte[] @ Hibernate for pictures

    @Column(nullable = false)
    private Long posNo;

    // Field can not be empty, so: optional = false, but what happens when leval has no note, picture or example ???????????
    @ManyToOne(optional = false)
    @JoinColumn (name = "level_id")
    private Level level;

}