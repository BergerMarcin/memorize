package pl.coderslab.memorize.services;

import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.dtos.LevelDTOList;

public interface LevelDTOListService {

    LevelDTOList getDBLevelsDTOByUserId(User user);
}
