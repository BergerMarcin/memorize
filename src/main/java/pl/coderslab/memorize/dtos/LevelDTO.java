package pl.coderslab.memorize.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LevelDTO {

    private Long levelId;
    private String levelName;
    private String levelShortName;
    private Long posNo;
    private List<LevelSiblingDTO> levelSiblingDTOS;

}
