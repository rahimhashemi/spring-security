package com.baeldung.roles.rolesauthorities.persistence;

import com.baeldung.roles.rolesauthorities.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}