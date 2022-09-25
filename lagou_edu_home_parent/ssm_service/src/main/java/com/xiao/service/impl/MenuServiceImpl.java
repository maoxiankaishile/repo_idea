package com.xiao.service.impl;

import com.xiao.dao.MenuMapper;
import com.xiao.domain.Menu;
import com.xiao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {
        List<Menu> subMenuList = menuMapper.findSubMenuListByPid(pid);

        return subMenuList;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> list = menuMapper.findAllMenu();
        return list;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }


}
