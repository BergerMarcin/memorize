package pl.coderslab.memorize.services;

import pl.coderslab.memorize.dtos.LevelDTO;

public interface LevelDTOService {

    LevelDTO getInitialLevelDTO (Long userId);

}
