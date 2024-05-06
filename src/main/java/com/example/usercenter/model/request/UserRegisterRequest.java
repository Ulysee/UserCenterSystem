package com.example.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 *  Register Data from frontend
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionID = 32131312414L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;


}
