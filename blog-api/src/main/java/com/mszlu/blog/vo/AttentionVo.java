package com.mszlu.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class AttentionVo {
    private String id;
    private String account;
    private String authorId;
    private Integer viewCounts;
    private String avatar;
    /*private List<String> attentionId;
    private List<String> collectionId;*/
    private String email;
    private String mobilePhoneNumber;
    private String nickname;

}
