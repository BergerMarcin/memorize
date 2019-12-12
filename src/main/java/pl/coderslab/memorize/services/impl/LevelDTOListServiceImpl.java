package pl.coderslab.memorize.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.memorize.domain.entities.Level;
import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.domain.repositories.LevelRepository;
import pl.coderslab.memorize.dtos.LevelDTO;
import pl.coderslab.memorize.dtos.LevelDTOList;
import pl.coderslab.memorize.dtos.LevelSiblingDTO;
import pl.coderslab.memorize.services.LevelDTOListService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LevelDTOListServiceImpl implements LevelDTOListService {

    LevelRepository levelRepository;
    public LevelDTOListServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public LevelDTOList getDBLevelsDTOByUserId(User user) {
        // IDEA of method
        // 1. All levels should be read from DB separately to levelList (in order to have all children because
        //      children are needed to write/map siblings at next point/step to LevelDTOList)
        // 2. levelList is mapped/rewrite to LevelDTOList

        // 0.Creating common variables
        List<Level> levelList = new ArrayList<>();
        List<LevelDTO> levelDTOListNew = new ArrayList();
        LevelDTOList levelDTOListObject = new LevelDTOList();


        // 1. CREATING levelList actual/updated for user

        // 1.1 Reading & adding top level-item from DB with children (taken separately because specific null parent field)
        //    Action taken no matter if user id known or not
        levelList.add(levelRepository.findFirstByParentIsNullOrderByPosNoWithChildren());

        // 1.2 Deppending if we have or not user
        if (user.getId() == 0L || user.getId() == null) {
            // If no user id then first levels are taken

            // 1.2.1a Reading & adding deeper levels (by first level item)
            Integer index = 0;
            Long levelParentId;
            while (levelList.get(index).getChildren() != null
// should be tested                    || levelList.get(index).getChildren().get(0).getName() != ""
                    || index < 4) {
                levelParentId = levelList.get(index).getId();
                // Adding next first (in position number order) level-item which parent (by parent_id) is higher level
                levelList.add(levelRepository.findFirstByParent_IdOrderByPosNoWithChildren(levelParentId));
                index++;
            }
        } else {
            // If there is user ID then read levels from DB

            // 1.2.1b Reading & adding deeper levels (by UserAppParam from DB - in fact UserAppParam is
            //      included in user (as UserAppParam is eager to User))
            Integer index = 0;
            Long levelId;
            while (levelList.get(index).getChildren() != null
// should be tested                    || levelList.get(index).getChildren().get(0).getName() != ""
                    || index < user.getUserAppParam().getViewLevelIds().size()
                    || user.getUserAppParam().getViewLevelIds().get(index) != null
                    || index < 4) {
                // Adding next first (in position number order) level-item which parent (by parent_id) is higher level
                levelId = user.getUserAppParam().getViewLevelIds().get(index);
                levelList.add(levelRepository.findFirstByIdWithChildren(levelId));
                index++;
            }
        }

        // 2. levelList is mapped/rewrite to LevelDTOList
        levelDTOListNew = mappingLevelToLevelDTO(levelList);
        levelDTOListObject.setLevelDTOS(levelDTOListNew);

        return levelDTOListObject;
    }

    private List<LevelDTO> mappingLevelToLevelDTO(List<Level> levelList) {
        List<LevelDTO> levelDTOListNew = new ArrayList<>();
        if (levelList != null && levelList.size() != 0) {
            for (int index=0; index <= levelList.size(); index++) {

                LevelDTO levelDTO = new LevelDTO();

                levelDTO.setLevelId(levelList.get(index).getId());
                levelDTO.setLevelName(levelList.get(index).getName().trim());
                levelDTO.setLevelShortName(shortenLevelName(levelList.get(index).getName()));
                levelDTO.setPosNo(levelList.get(index).getPosNo());

                if (index > 0) {
                    LevelSiblingDTO levelSiblingDTO = new LevelSiblingDTO();
//TODO przerzucenie childrenów do siblingsów
//                    levelDTO.setLevelSiblingDTOS(levelList.get(index).getChildren());
                } else {
                    levelDTO.setLevelSiblingDTOS(null);
                }

                levelDTOListNew.add(levelDTO);
            }
        }
        return levelDTOListNew;
    }

    private String shortenLevelName (String name) {
        // Shorten name to 21 chars to see it in select of html/jsp
        //      (example change name: "Java OOP (Object Oriented Programming" -> shortName: "Java OOP (Object...")
        String shortName = name.trim();
        if (shortName.length()>21) {
            shortName.substring(0, 18);
            if (shortName.contains(" ")) {
                String[] strArr = shortName.split(" ");
                shortName="";
                for (String str : strArr) {
                    if (shortName.length()+str.length() <=15) {
                        shortName.concat(str).concat(" ");
                    }
                }
                shortName.trim();
                shortName.concat("...");
            }
        }
        return shortName;
    };

}
