package com.mszlu.blog.vo.params;

import lombok.Data;

@Data
public class AskMyArticleParam {
    private Integer currentPage;
    private Integer pageSize;
    private String userId;
}
