package pl.coderslab.memorize.dtos;

import lombok.Data;

@Data
public class NoteDTO {

    private Long id;
    private String htmlText;
    private String plainText;
    private Long posNo;

}
