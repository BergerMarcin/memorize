package pl.coderslab.memorize.services;

import pl.coderslab.memorize.dtos.WebAppParamDTO;

public interface WebAppParamService {

    WebAppParamDTO readDBWebAppParamByUserName (String loginUserName);
    //TODO: saveWebAppParamByUserName
    // void saveWebAppParamByUserName (WebAppParamDTO webAppParamDTO);

}
