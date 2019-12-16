package pl.coderslab.memorize.dtos;

import lombok.Data;

@Data
public class PictureDTO {

    private Long id;
    private String name;
    private String description;
    private String filename;
    private String contentType;
    private byte[] fileData;
    private Long posNo;

}
