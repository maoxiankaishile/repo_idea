package com.xiao.controller;

import com.xiao.domain.Menu;
import com.xiao.domain.PromotionAd;
import com.xiao.domain.ResponseResult;
import com.xiao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    //回显菜单信息（包括父子菜单的全部信息）
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam Integer id){

        if (id == -1){
        //添加操作 回显不需要查询 menu信息
            List<Menu> parentMenuList = menuService.findSubMenuListByPid(-1);

        //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",parentMenuList);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;

        }else {
         //修改操作 带回显
            Menu menuInfo = menuService.findMenuById(id);
            List<Menu> parentMenuList  = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menuInfo);
            map.put("parentMenuList",parentMenuList);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;


        }


    }

}
