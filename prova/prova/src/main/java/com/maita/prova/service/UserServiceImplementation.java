package com.maita.prova.service;

import com.maita.prova.repository.UserRepository;
import com.maita.prova.config.JwtProvider;
import com.maita.prova.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("user not found with id: " + userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        if (email == null) {
            throw new Exception("Provide a valid jwt token");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found with email " + email);
        }

        return user;
    }

    @Override
    public void deleteUser(User user, String jwt) throws Exception {

        User userValidated = findUserByJwt(jwt);
        User userOwner = findUserById(user.getId());

        if (!verificationUser(userOwner, jwt) || !verificationUser(userValidated, jwt)) {

            throw new Exception("Utente non abilitato alla cancellazione di account");
        }

        userRepository.delete(user);

    }

    @Override
    public boolean verificationUser(User user, String jwt) throws Exception {

        User userValidated = findUserByJwt(jwt);

        if (userValidated.getRole().equals(User.Role.ADMIN)) {
            return true;
        }

        User userChecker = findUserById(user.getId());

        if (!userValidated.equals(userChecker)) {
            return false;
        }
        ;

        return true;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
