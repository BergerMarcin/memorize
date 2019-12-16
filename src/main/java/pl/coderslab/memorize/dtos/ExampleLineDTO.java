package pl.coderslab.memorize.dtos;

import lombok.Data;

@Data
public class ExampleLineDTO {

    private Long id;
    private String htmlLine;
    private String plainLine;
    private String plainDescr;
    private String htmlDescr;
    private Long posNo;


}
