package com.codecool.bookify.service;

import com.codecool.bookify.exceptions.NotFoundException;
import com.codecool.bookify.model.Company;
import com.codecool.bookify.model.Role;
import com.codecool.bookify.model.User;
import com.codecool.bookify.repository.CompanyRepository;
import com.codecool.bookify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements HelperService<User> {

    protected UserRepository userRepository;
    protected CompanyRepository companyRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
//        this.roleService = roleService;
    }

//    @Autowired
//    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(User user, Long id) {
    }

    @Override
    public void insert(User user) {
        User newUser = new User();
        newUser.setId(null);
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setPhone(user.getPhone());
        newUser.setActive(true);
        newUser.setCompany(user.getCompany());
        newUser.getRoles().addAll(
                user.getRoles()
                        .stream()
                        .map(u -> {
                            Role role = roleService.findByName(u.getName());
                            role.getUsers().add(user);
                            return role;
                        }).collect(Collectors.toSet()));
        userRepository.save(newUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
