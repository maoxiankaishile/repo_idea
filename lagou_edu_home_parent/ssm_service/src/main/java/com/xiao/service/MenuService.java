package com.xiao.service;

import com.xiao.domain.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 查询所有的父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(Integer pid);

    /**
     * 查询菜单列表
     */
    public List<Menu> findAllMenu();

    Menu findMenuById(Integer id);
}
