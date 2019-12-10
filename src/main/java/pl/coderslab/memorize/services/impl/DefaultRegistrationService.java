package pl.coderslab.memorize.services.impl;

import pl.coderslab.memorize.domain.entities.Role;
import pl.coderslab.memorize.domain.entities.User;
import pl.coderslab.memorize.domain.repositories.RoleRepository;
import pl.coderslab.memorize.domain.repositories.UserRepository;
import pl.coderslab.memorize.dtos.RegistrationDataDTO;
import pl.coderslab.memorize.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultRegistrationService implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DefaultRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(RegistrationDataDTO registrationData) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registrationData, User.class);
        user.setActive(Boolean.TRUE);
        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
        user.setPassword(encodedPassword);

        // Ustawienia roli dla użytkwonika
        Role roleUser = roleRepository.getByName("ROLE_USER");
        user.getRoles().add(roleUser);

        userRepository.save(user);
    }
}
