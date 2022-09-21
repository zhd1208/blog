package com.mszlu.blog.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.dao.mapper.SysUserMapper;
import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.service.impl.LoginServiceImpl;
import com.mszlu.blog.utils.JWTUtils;
import com.mszlu.blog.utils.MailClient;
import com.mszlu.blog.utils.NumUtil;
import com.mszlu.blog.vo.ErrorCode;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.ForgetParam;
import com.mszlu.blog.vo.params.RegisterParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("forget")
public class ForgetController {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public Result updataPassword(@RequestBody ForgetParam forgetParam){
        SysUser sysUser=new SysUser();
        String code = forgetParam.getCode();
        String email= forgetParam.getEmail();
        String password= forgetParam.getPassword();
        String account= forgetParam.getAccount();
        String mobilePhoneNumber= forgetParam.getMobilePhoneNumber();

        sysUser=sysUserService.findUserByEmail(email);
        /*if (sysUser==null){
            return Result.fail(404,"该邮箱未与该账号绑定！");
        }*/
        if (!account.equals(sysUser.getAccount())){
            return Result.fail(404,"该邮箱未与该账号绑定!");
        }
        sysUser=sysUserService.findUserByAccount(account);
        if (sysUser==null){
            return Result.fail(404,"该账号未注册！");
        }
        if (!code.equals(redisTemplate.opsForValue().get(forgetParam.getUuid()+".code"))) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"邮箱验证码不正确");
        }
        sysUser.setPassword(DigestUtils.md5Hex(password + LoginServiceImpl.slat));
        sysUser.setLastLogin(System.currentTimeMillis());
        return this.sysUserService.updatePassword(sysUser);
    }
    @PostMapping("code")
    public Result putCode(@RequestBody ForgetParam forgetParam){
        SysUser sysUser=new SysUser();
        String code = NumUtil.createCode();
        String uuid=String.valueOf(UUID.randomUUID());
        String emal= forgetParam.getEmail();
        String account= forgetParam.getAccount();
        String password= forgetParam.getPassword();
        String mobilePhoneNumber= forgetParam.getMobilePhoneNumber();
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getEmail,emal);
        queryWrapper.last("limit 1");
        sysUser=this.sysUserMapper.selectOne(queryWrapper);
        if (sysUser==null){
            return Result.fail(404,"该邮箱未与账号绑定！");
        }
        if (!account.equals(sysUser.getAccount())){
            return Result.fail(404,"该邮箱未与该账号绑定!");
        }
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        sysUser=this.sysUserMapper.selectOne(queryWrapper);
        if (sysUser==null){
            return Result.fail(404,"该账号未注册！");
        }
        try{
            mailClient.sendMail(forgetParam.getEmail(), "星猫小站","您的找回密码验证码为:"+code);
        }catch (Exception e){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"邮箱账号非法，请输入正确邮箱！");
        }
        redisTemplate.opsForValue().set(uuid+".code", code,5, TimeUnit.MINUTES);
        return Result.success("邮箱发送成功！",uuid);
    }
}
