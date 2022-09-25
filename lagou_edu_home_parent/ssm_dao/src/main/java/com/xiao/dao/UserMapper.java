package com.xiao.dao;

import com.xiao.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有的用户
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(@Param("id") Integer id, @Param("status") String status);

    /**
     * 用户登陆
     */
    public User login(User user);

    /**
     * 根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     */

    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据PID 查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> id);


    public String xiao();


}

