package com.mszlu.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyArticles {
    private List<MyArticle> myArticle;
    private Integer total;
}
