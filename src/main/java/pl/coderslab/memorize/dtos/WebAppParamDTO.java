package pl.coderslab.memorize.dtos;

import lombok.Data;

import java.util.List;

/**
 * Parameters object for web application to store:
 *  - actual data levels chosen by the user
 *  - any other if needed
 * That data will be stored in session as a object as a field "webAppParam" (see class HomePageController)
 */
@Data
public class WebAppParamDTO {

    private Long userId;
    private String userName;
    private String userRoleName;
    private LevelDTOList levelDTOList;
    private String alertMessage;

}
