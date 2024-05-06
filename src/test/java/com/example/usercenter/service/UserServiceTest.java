package com.example.usercenter.service;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.example.usercenter.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;


    @Test
    void testAddUser(){
        User user = new User();
        user.setId(0L);
        user.setUserAccount("123");
        user.setUserName("d2e");
        user.setAvatarUrl("21e");
        user.setGender(0);
        user.setUserPassword("23");
        user.setPhoneNum("123");
        user.setEmail("213");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIfDelete(0);
        boolean result = userService.save(user);
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() throws NoSuchAlgorithmException {
        String userAccount = "xzyp";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
    }

    @Test
    void userLogin() {
        String enteredUserAccount = "xzy1";
        String enteredPassword = "12345679";
        HttpServletRequest request;

    }
}