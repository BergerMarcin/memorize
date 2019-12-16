package pl.coderslab.memorize.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ExampleDTO {

    private Long id;
    private String name;
    private String description;
    private Long posNo;
    private List<ExampleLineDTO> exampleLineDTOS;

}
