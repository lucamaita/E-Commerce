package com.SCAI.ecommerce.config;

import com.SCAI.ecommerce.repository.UserRepository;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.model.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder; // Utilizzato per crittografare la password

    public InitialDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Controlla se non c'è già un admin nel database
        if (!userRepository.existsByRole(Role.ADMIN)) {
            // Se non c'è un admin, crea un admin predefinito
            User adminUser = new User();
            adminUser.setEmail("admin@mariorossi.com");
            adminUser.setFullName("Mario Rossi");
            adminUser.setPassword(passwordEncoder.encode("admin1234"));
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);
        }
    }
}