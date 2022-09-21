package com.mszlu.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    private MailClient mailClient;
    public void s(){
        mailClient.sendMail("443360198@qq.com","test","ww");
    }
    public static void main(String[] args) {
        new test().s();
    }
}
