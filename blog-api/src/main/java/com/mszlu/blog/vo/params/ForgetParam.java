package com.mszlu.blog.vo.params;

import lombok.Data;

@Data
public class ForgetParam {
    private String uuid;
    private String account;
    private String password;
    private String password1;
    private String code;
    private String email;
    private String mobilePhoneNumber;
}
