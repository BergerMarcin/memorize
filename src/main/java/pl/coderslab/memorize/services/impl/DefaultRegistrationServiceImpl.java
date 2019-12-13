package pl.coderslab.memorize.services.impl;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.memorize.domain.entities.Role;
import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.domain.entities.UserAppParam;
import pl.coderslab.memorize.domain.repositories.LevelRepository;
import pl.coderslab.memorize.domain.repositories.RoleRepository;
import pl.coderslab.memorize.domain.repositories.UserRepository;
import pl.coderslab.memorize.dtos.RegistrationDataDTO;
import pl.coderslab.memorize.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional @Slf4j
public class DefaultRegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LevelRepository levelRepository;

    public DefaultRegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                                          RoleRepository roleRepository, LevelRepository levelRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public void register(RegistrationDataDTO registrationData) {
        log.debug("Registration data: {}", registrationData);
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registrationData, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
        user.setPassword(encodedPassword);

        // Ustawienia roli dla użytkwonika
        Role roleUser = roleRepository.getByName("ROLE_USER");
        user.getRoles().add(roleUser);

        //
        //TODO Check Set first/registration parameters (levels to be viewed)
        UserAppParam userAppParam = new UserAppParam();
        List<Long> viewLevelsId = new ArrayList<>();
        viewLevelsId.add(levelRepository.findFirstWithChildrenByParentIsNullOrderByPosNo().getId());
//TODO
//        userAppParam.setViewLevelsId(viewLevelsId);
        user.setUserAppParam(userAppParam);

        userRepository.save(user);
    }
}
