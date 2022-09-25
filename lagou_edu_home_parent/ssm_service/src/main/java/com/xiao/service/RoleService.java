package com.xiao.service;

import com.xiao.domain.Role;
import com.xiao.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /**
     * 角色列表查询&条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询菜单信息
     */
    public List<String> findMenuByRoleId(Integer roleId);

    void RoleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    void deleteRole(Integer id);
}
