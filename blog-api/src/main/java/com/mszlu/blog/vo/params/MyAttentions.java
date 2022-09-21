package com.mszlu.blog.vo.params;

import com.mszlu.blog.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class MyAttentions {
    private List<UserVo> userVoList;
    int total;

    public MyAttentions(List<UserVo> userVoList, int total) {
        this.userVoList = userVoList;
        this.total = total;
    }

    public MyAttentions() {
    }
}
