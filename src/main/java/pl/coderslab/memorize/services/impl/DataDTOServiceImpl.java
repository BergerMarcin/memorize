package pl.coderslab.memorize.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.memorize.domain.entities.*;
import pl.coderslab.memorize.domain.repositories.ExampleRepository;
import pl.coderslab.memorize.domain.repositories.NoteRepository;
import pl.coderslab.memorize.domain.repositories.PictureRepository;
import pl.coderslab.memorize.dtos.*;
import pl.coderslab.memorize.services.DataDTOService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DataDTOServiceImpl implements DataDTOService {

    private NoteRepository noteRepository;
    private PictureRepository pictureRepository;
    private ExampleRepository exampleRepository;

    public DataDTOServiceImpl(NoteRepository noteRepository,
                              PictureRepository pictureRepository,
                              ExampleRepository exampleRepository) {
        this.noteRepository = noteRepository;
        this.pictureRepository = pictureRepository;
        this.exampleRepository = exampleRepository;
    }

    @Override
    public List<DataDTO> getDBDataDTOByLevelId(Long levelId) {
        log.debug("!!!! Manual Debugger !!!! DataDTOServiceImpl getDBDataDTOByLevelId: levelId taken: {}", levelId);
        // validation
        if (levelId == null || levelId < 0) {
            return null;
        }

        // 0.Creating common variables
        //TODO: actual level_id
        List<DataDTO> dataDTOList = new ArrayList<>();
        List<Note> notes = noteRepository.findAllByLevelIdOrderByPosNo(7L);
        List<Picture> pictures = pictureRepository.findAllByLevelIdOrderByPosNo(7L);
        List<Example> examples = exampleRepository.findAllByLevelIdOrderByPosNo(7L);

        // map notes to dataDTOList
        for (Note note: notes) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setPosNo(note.getPosNo());
            dataDTO.setType(1);
            ModelMapper modelMapper = new ModelMapper();
            NoteDTO noteDTO = modelMapper.map(note, NoteDTO.class);
// OLD WAY:
//            NoteDTO noteDTO = new NoteDTO();
//            noteDTO.setId(note.getId());
//            noteDTO.setHtmlText(note.getHtmlText());
//            noteDTO.setPlainText(note.getPlainText());
//            noteDTO.setPosNo(note.getPosNo());
            log.debug("!!!! Manual Debugger !!!! dataDTOServiceImpl getDBDataDTOByLevelId: note: {}", note);
            log.debug("!!!! Manual Debugger !!!! dataDTOServiceImpl getDBDataDTOByLevelId: noteDTO: {}", noteDTO);
            dataDTO.setNoteDTO(noteDTO);
            dataDTOList.add(dataDTO);
        }
        // map pictures to dataDTOList
        for (Picture picture: pictures) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setPosNo(picture.getPosNo());
            dataDTO.setType(2);
            ModelMapper modelMapper = new ModelMapper();
            PictureDTO pictureDTO = modelMapper.map(picture, PictureDTO.class);
// OLD WAY:
//            PictureDTO pictureDTO = new PictureDTO();
//            pictureDTO.setId(picture.getId());
//            pictureDTO.setName(picture.getName());
//            pictureDTO.setDescription(picture.getDescription());
//            pictureDTO.setPosNo(picture.getPosNo());
            dataDTO.setPictureDTO(pictureDTO);
            dataDTOList.add(dataDTO);
        }
        // map examples with exampleLines to dataDTOList
        for (Example example: examples) {
            DataDTO dataDTO = new DataDTO();
            dataDTO.setPosNo(example.getPosNo());
            dataDTO.setType(3);
            ModelMapper modelMapper = new ModelMapper();
// TODO: To chceck if example lines are copied/mapped
            ExampleDTO exampleDTO = modelMapper.map(example, ExampleDTO.class);
            log.debug("!!!! Manual Debugger !!!! dataDTOServiceImpl getDBDataDTOByLevelId: example: {}", example);
            log.debug("!!!! Manual Debugger !!!! dataDTOServiceImpl getDBDataDTOByLevelId: exampleDTO: {}", exampleDTO);
            dataDTO.setExampleDTO(exampleDTO);
            dataDTOList.add(dataDTO);
        }

        // sorting by posNo all list elements
        dataDTOList = dataDTOList.stream().sorted(Comparator.comparing(DataDTO::getPosNo)).collect(Collectors.toList());
        // DONE! :)
        
        return dataDTOList;
    }
}
