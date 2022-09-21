package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.dao.mapper.AttentionMapper;
import com.mszlu.blog.dao.pojo.Attention;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionServiceImpl {
    @Autowired
    private AttentionMapper attentionMapper;
    public Result attentionAuthor(Attention attention) {
        /*Attention attention1=new Attention();
        LambdaQueryWrapper<Attention> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Attention::getAuthorId,attention.getAuthorId());
        List<Attention> attentionList=attentionMapper.selectList(queryWrapper);
        for (Attention attention2:attentionList){
            if (attention2.getAttentionId()==null){
                Integer id = attention2.getId();
                LambdaQueryWrapper<Attention> queryWrapper1=new LambdaQueryWrapper<>();
                queryWrapper1.eq(Attention::getId,id);
                attentionMapper.update(attention,queryWrapper1);
                return Result.success("关注成功！",null);
            }
        }*/
        LambdaQueryWrapper<Attention> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attention::getAuthorId, attention.getAuthorId());
        queryWrapper.eq(Attention::getAttentionId, attention.getAttentionId());
        Attention attention1 = attentionMapper.selectOne(queryWrapper);
        if (attention1 == null) {
            attentionMapper.insert(attention);
            return Result.success("关注成功！", null);
        }
        if (attention1 != null) {
            attentionMapper.delete(queryWrapper);
            return Result.fail(300,"您已关注该作者，无需重复关注！");
        }
        return Result.fail(999,"未知错误！");
    }


    public Result unAttentionAuthor(Attention attention) {
        LambdaQueryWrapper<Attention> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attention::getAuthorId, attention.getAuthorId());
        queryWrapper.eq(Attention::getAttentionId, attention.getAttentionId());
        Attention attention1 = attentionMapper.selectOne(queryWrapper);
        if (attention1 == null) {
            return Result.fail(300, "您未关注该作者，无需取消关注！");
        }
        if (attention1!=null) {
            attentionMapper.delete(queryWrapper);
            return Result.success("取消关注成功！", null);
        }
        return Result.fail(999,"未知错误！");
    }

    public Result judgeAttention(Attention attention) {
        LambdaQueryWrapper<Attention> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attention::getAuthorId, attention.getAuthorId());
        queryWrapper.eq(Attention::getAttentionId, attention.getAttentionId());
        Attention attention1 = attentionMapper.selectOne(queryWrapper);
        if (attention1==null){
            return Result.success(false);
        }
        if (attention1!=null){
            return Result.success(true);
        }
        return Result.fail(999,"未知错误！");
    }
}
