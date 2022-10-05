package com.example.demo.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.IUserDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.impl.UserService;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    IUserDao userDao;

    ArrayList<UserModel> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        fillList();
    }

    @Test
    public void getTest() throws GenericException {
        Collection<Long> c = Collections.singleton(1L);
        when(userDao.get(c)).thenReturn(list);
        Collection<UserModel> response = userService.get(c);
        assertEquals(1, response.size());
        verify(userDao, times(1)).get(c);
    }

    public void fillList() {
        list.add(new UserModel("jroigdo", "springboot", Arrays.asList(new RoleModel("Admin"))));
    }

}
