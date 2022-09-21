package com.mszlu.blog.vo.params;

import lombok.Data;

@Data
public class RegisterParam {
    private String uuid;

    private String account;

    private String password;

    private String nickname;

    private String email;

    private String code;
}
