package com.xiao.service;

import com.github.pagehelper.PageInfo;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.Role;
import com.xiao.domain.User;
import com.xiao.domain.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户（分页）
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(Integer id,String status);

    /**
     * 用户登陆
     */
    public User login(User user) throws Exception;

    /**
     * 根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /**
     * 获取用户权限
     */
    ResponseResult getUserPermissions(Integer id);

}
