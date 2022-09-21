package com.mszlu.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Collection {
    @TableId(type = IdType.AUTO)
    private int id;
    private Long authorId;
    private Long collectionId;
}
