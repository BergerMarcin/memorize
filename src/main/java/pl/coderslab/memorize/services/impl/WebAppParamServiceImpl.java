package pl.coderslab.memorize.services.impl;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WebAppParamServiceImpl implements WebAppParamService {

    private LevelDTOListService levelDTOListService;
    private UserRepository userRepository;
    public WebAppParamServiceImpl(LevelDTOListService levelDTOService, UserRepository userRepository) {
        this.levelDTOListService = levelDTOService;
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

        User user = new User();
        user = userRepository.findFirstByUsername(loginUserName);
        log.debug("!!!! Manual Debugger !!!! WebAppParamServiceImpl readDBWebAppParamByUserName: user taken for {}: {}", loginUserName, user);

        if (loginUserName.equals("anonymousUser") || loginUserName.equals("") || loginUserName == null
                || user == null || user.getId()<1L) {
            webAppParamDTO.setUserId(0L);
            webAppParamDTO.setUserName("");
            webAppParamDTO.setUserRoleName("");
            User user2 = new User();
            user2.setId(0L);
            log.debug("!!!! Manual Debugger !!!! WebAppParamServiceImpl readDBWebAppParamByUserName: " +
                    "user given to getDBLevelsDTOByUserId: {}", user2);
            webAppParamDTO.setLevelDTOList(levelDTOListService.getDBLevelsDTOByUserId(user2));
            log.debug("!!!! Manual Debugger !!!! WebAppParamServiceImpl readDBWebAppParamByUserName: " +
                    "webAppParamDTO.setLevelDTOList(levelDTOListService.getDBLevelsDTOByUserId(user)) passed!!!!");
            webAppParamDTO.setAlertMessage("Please login to see all data");
        } else {
            webAppParamDTO.setUserId(user.getId());
            webAppParamDTO.setUserName(user.getUsername());
            //TODO: method to read role - in fact not applied role functionality in application
            // webAppParamDTO.setUserRoleName(user.getRoles().stream().findFirst().get().getName());
            webAppParamDTO.setUserRoleName("");
            webAppParamDTO.setLevelDTOList(levelDTOListService.getDBLevelsDTOByUserId(user));
            log.debug("!!!! Manual Debugger !!!! WebAppParamServiceImpl readDBWebAppParamByUserName webAppParamDTOMain: {}",
                    webAppParamDTOMain);
            if (webAppParamDTOMain.getAlertMessage() == null) {
                webAppParamDTOMain.setAlertMessage("");
            }
            // Overwrite alert message from actual one
            if (!webAppParamDTOMain.getAlertMessage().equals("")) {
                webAppParamDTO.setAlertMessage(webAppParamDTOMain.getAlertMessage());
            }
        }

        return webAppParamDTO;
    }

}
