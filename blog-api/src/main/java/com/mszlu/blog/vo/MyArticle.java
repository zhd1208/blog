package com.mszlu.blog.vo;

import lombok.Data;

@Data
public class MyArticle {

    // "author":"z007"}],"total":2}
    private String id;
    private String title;
    private Integer viewCounts;
    private String authorId;
    private String updateTime;
    private Integer starCounts;
    private String picture;
    private String authorNickName;
}
