package com.mszlu.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ImgLink {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long articleId;
    private String img;
}
