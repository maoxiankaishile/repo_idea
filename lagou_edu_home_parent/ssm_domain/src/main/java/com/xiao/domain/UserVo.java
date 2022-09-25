package com.xiao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Integer currentPage;
    private Integer pageSize;
    private String username;
    private Integer userId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startCreateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endCreateTime;

    private List<Integer> RoleIdList;

}
