package com.mszlu.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Attention {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long authorId;
    private Long attentionId;

}
