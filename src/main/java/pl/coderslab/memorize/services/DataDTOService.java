package pl.coderslab.memorize.services;

import pl.coderslab.memorize.dtos.DataDTO;

import java.util.List;

public interface DataDTOService {

    List<DataDTO> getDBDataDTOByLevelId(Long levelId);

}
