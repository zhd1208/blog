package com.mszlu.blog.controller;

import com.mszlu.blog.dao.pojo.Attention;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.service.impl.AttentionServiceImpl;
import com.mszlu.blog.service.impl.SysUserServiceImpl;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    private AttentionServiceImpl attentionService;

    /**
     * 关注作者
     * @param attention
     * @return
     */
    @Transactional
    @PostMapping
    public Result attentionAuthor(@RequestBody Attention attention){
        return attentionService.attentionAuthor(attention);
    }

    /**
     * 取消关注
     * @param attention
     * @return
     */
    @Transactional
    @PostMapping("/unAttention")
    public Result unAttentionAuthor(@RequestBody Attention attention){
        return attentionService.unAttentionAuthor(attention);
    }

    /**
     * 判断是否关注了
     * @param attention
     * @return
     */
    @PostMapping("/judgeAttention")
    public Result judgeAttention(@RequestBody Attention attention){
        return attentionService.judgeAttention(attention);
    }

    /**
     * 根据关注id查询文章
     */
    @Autowired
    private SysUserServiceImpl sysUserService;

    @GetMapping("/articles/{id}")
    public Result findArticlesByAttentionId(@PathVariable("id") String id){

        Long attentionId=Long.valueOf(id);
        return Result.success(sysUserService.findUserVoById(attentionId));
    }
}
