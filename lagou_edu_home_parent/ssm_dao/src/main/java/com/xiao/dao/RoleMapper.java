package com.xiao.dao;

import com.xiao.domain.Role;
import com.xiao.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /**
     * 角色列表查询&条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询菜单信息
     */
    public List<String> findMenuByRoleId(Integer roleId);


    /**
     *  角色菜单关联
     */
    public void  RoleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色菜单关联信息
     */
    public void deleteRoleContextMenu(Integer id);

    /**
     * 删除角色
     */
    void deleteRole(Integer id);


}
