package com.mszlu.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.dao.pojo.MyStar;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MyStarMapper extends BaseMapper<MyStar> {

    @Select("select article_id from ms_mystar where user_id = #{userId}")
    public List<Long> findMyStarId(@Param("userId") Long userId);
}
