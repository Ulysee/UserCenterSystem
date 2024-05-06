package com.example.usercenter.service;

import com.example.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;

/**
* @author xzy
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-06 14:38:37
*/
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount useraccount
     * @param userPassword userpassword
     * @param checkPassword usercheckpassword
     * @return userRegister
     */


    long userRegister(String userAccount, String userPassword, String checkPassword) throws NoSuchAlgorithmException;

    User userLogin(String enteredUserAccount,String enteredUserPassword, HttpServletRequest request);

    int userLogout(HttpServletRequest request);
}
