package pl.coderslab.memorize.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.domain.repositories.UserRepository;
import pl.coderslab.memorize.dtos.WebAppParamDTO;
import pl.coderslab.memorize.services.LevelDTOListService;
import pl.coderslab.memorize.services.WebAppParamService;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;


@Service
@Transactional
public class WebAppParamServiceImpl implements WebAppParamService {

    private LevelDTOListService levelDTOListService;
    private UserRepository userRepository;
    public WebAppParamServiceImpl(LevelDTOListService levelDTOService, UserRepository userRepository) {
        this.levelDTOListService = levelDTOListService;
        this.userRepository = userRepository;
    }

    // Gets user app parameters from DB, alert message is rewritten or asking to login
    // In case authorised user set settings from entity of the user (UserAppParam). If UserAppParam entity is empty
    //    set only first levels to data (i.e. to notes/pictures/examples).
    // In case anonymous user set only first levels to data (i.e. to notes/pictures/examples). BTW, for anonymous user
    //    changing reading view is blocked (user does not see button to go other level)
    @Override
    public WebAppParamDTO readDBWebAppParamByUserName(String loginUserName) {
        WebAppParamDTO webAppParamDTO = new WebAppParamDTO();

        User user;
        try {
            user = userRepository.findFirstByUsername(loginUserName);
        } catch (Exception e) {
            user = null;
        }

        if (loginUserName.equals("anonymous") || loginUserName.equals("") || loginUserName == null
                || user == null || user.getId()<1L) {
            webAppParamDTO.setUserId(0L);
            webAppParamDTO.setUserName("");
            webAppParamDTO.setUserRoleName("");
            webAppParamDTO.setLevelDTOList(levelDTOListService.getDBLevelsDTOByUserId(user));
            webAppParamDTO.setAlertMessage("Please login to see all data");
        } else {
            webAppParamDTO.setUserId(user.getId());
            webAppParamDTO.setUserName(user.getUsername());
            //TODO: method to read role - in fact not applied role functionality in application
            // webAppParamDTO.setUserRoleName(user.getRoles().stream().findFirst().get().getName());
            webAppParamDTO.setUserRoleName("");
            webAppParamDTO.setLevelDTOList(levelDTOListService.getDBLevelsDTOByUserId(user));
            // Overwrite alert message from actual one
            if (! webAppParamDTOMain.getAlertMessage().equals("")) {
                webAppParamDTO.setAlertMessage(webAppParamDTOMain.getAlertMessage());
            }
        }

        return webAppParamDTO;
    }

}
