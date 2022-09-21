package com.mszlu.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ms_mystar")
public class MyStar {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long articleId;
}
