package pl.coderslab.memorize.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "levels")
@Getter @Setter
@ToString(exclude = {"children", "notes", "pictures", "examples"})
@EqualsAndHashCode(of = "id")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //id child (child is that entity object)
    // Child has/knows Parent object by parent_id
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Level parent;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "VARCHAR(650)")
    private String description;
    // Children are ordered/sorted automatically by posNo (position number) field
    @Column(nullable = false)
    private Long posNo;
    @Column
    private Integer access = 5;         // ????????? Szyfrowanie dostępu ?????????????

    // Children list in parent object
    // List object acc./referred to parent_id
    // List ordered/sorted automatically by posNo (position number) field
    @OneToMany
    @OrderBy("posNo")       // Just return list ordered by column posNo
//    @OrderColumn          // Either @OrderColumn or @OrderBy("posNo").
                            // @OrderColumn sets order in database of lines. It impacts on saving/persisting line localisation
                            // (so slow down DB to rewrite DB lisnes to keep it in order more
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private List<Level> children = new ArrayList();

    // In case update or delete level, notes are created, updated or deleted automatically with level
    @OneToMany(mappedBy = "level", cascade = {CascadeType.ALL})
    private List<Note> notes;

    // In case update or delete level, pictures are created, updated or deleted automatically with level
    @OneToMany(mappedBy = "level", cascade = {CascadeType.ALL})
    private List<Picture> pictures;

    // In case update or delete level, examples are created, updated or deleted automatically with level
    @OneToMany(mappedBy = "level", cascade = {CascadeType.ALL})
    private List<Example> examples;
}