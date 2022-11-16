package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.UserModel;
import com.example.demo.service.IUserService;

@Service
public class UserService extends GenericService<UserModel, Long> implements IUserService, UserDetailsService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = this.userDao.findByUsername(username);

        if (userModel == null) {
            log.error("The user doesn't exists");
            throw new UsernameNotFoundException("The user doesn't exists");
        }

        List<GrantedAuthority> authorities = userModel.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        authorities.forEach(authority -> log.info("Role: ".concat(authority.getAuthority())));

        return new User(userModel.getUsername(), userModel.getPassword(), !userModel.isDisabled(), true, true, true,
                authorities);
    }

    @Override
    public UserModel getByUsername(String username) throws GenericException {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), e, 500);
        }
    }

}
