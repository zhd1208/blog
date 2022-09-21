package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.ForgetParam;

public interface ForgetService {
    Result updatePassword(ForgetParam forgetParam);
}
