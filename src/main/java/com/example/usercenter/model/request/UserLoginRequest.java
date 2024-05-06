package com.example.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 *  Register Data from frontend
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionID = 3213131244L;

    private String userAccount;

    private String userPassword;

}
