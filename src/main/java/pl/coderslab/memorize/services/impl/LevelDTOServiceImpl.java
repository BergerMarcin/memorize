package pl.coderslab.memorize.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.memorize.dtos.LevelDTO;
import pl.coderslab.memorize.services.LevelDTOService;

@Service
@Transactional
public class LevelDTOServiceImpl implements LevelDTOService {

    @Override
    public LevelDTO getInitialLevelDTO(Long userID) {
        return null;
    }
}
