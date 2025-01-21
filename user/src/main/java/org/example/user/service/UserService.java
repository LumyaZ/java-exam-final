package org.example.user.service;

import org.example.user.entity.User;
import org.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User saveUser(User user);

    public void deleteUser(Long id);
}
