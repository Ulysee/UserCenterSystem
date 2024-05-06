package com.example.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 *  Register Data from frontend
 */
@Data
public class UserDeleteRequest implements Serializable {
    private static final long serialVersionID = 321313132144L;

    private String userAccount;


}
