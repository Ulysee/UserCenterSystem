package com.example.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usercenter.constant.UserConstant;
import com.example.usercenter.model.User;
import com.example.usercenter.service.UserService;
import com.example.usercenter.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.usercenter.constant.UserConstant;

/**
* @author xzy
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-06 14:38:37
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) throws NoSuchAlgorithmException {
        // 1. check
        // check if legal
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length() < 4){
            return -1;
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }
        //if user account contains special character
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }
        // if exists same user account
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //datasource object for searching
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            return -1;
        }
        // password if equals to checkpassword
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        //2. encryption
        String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT+userPassword).getBytes());

        //3. insert user data
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if(!saveResult)
            return -1;
        return user.getId();
    }

    @Override
    public User userLogin(String enteredUserAccount,String enteredUserPassword, HttpServletRequest request){
        // 1. Check entered data is correct or not
        if(StringUtils.isAnyBlank(enteredUserAccount,enteredUserPassword)){
            return null;
        }
        if(enteredUserAccount.length() < 4){
            return null;
        }
        if(enteredUserPassword.length() < 8){
            return null;
        }
        //if user account contains special character
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(enteredUserAccount);
        if(matcher.find()){
            return null;
        }
        // if

        // 2. Inquire user account is registered or not
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", enteredUserAccount);
        long count = this.count(queryWrapper);
        if(count != 1){
            System.out.println("This user account is not registered");
            return null;
        }
        // 2. Inquire entered user password is equal to correct user password
        String enteredEncryptPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT+enteredUserPassword).getBytes());
        queryWrapper.eq("UserPassword", enteredEncryptPassword);
        count = this.count(queryWrapper);
        if(count != 1){
            System.out.println("This user password is not correct");
            return null;
        }
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("user login failed, user account cannot match password");
            return null;
        }
        //3. delete sensitive information from user
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUserAccount(user.getUserName());
        safeUser.setUserName(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhoneNum(user.getPhoneNum());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setCreateTime(user.getCreateTime());
        safeUser.setRole(user.getRole());

        //4. record user login status(session)
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);

        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return 1;
    }


}




