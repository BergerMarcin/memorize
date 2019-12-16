package pl.coderslab.memorize.dtos;

import lombok.Data;

@Data
public class DataDTO {

    private Long posNo;
    private Integer type;
    private NoteDTO noteDTO;
    private PictureDTO pictureDTO;
    private ExampleDTO exampleDTO;

}
