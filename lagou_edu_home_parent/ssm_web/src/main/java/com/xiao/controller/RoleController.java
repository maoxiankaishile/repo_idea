package com.xiao.controller;

import com.xiao.domain.Menu;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.Role;
import com.xiao.domain.RoleMenuVo;
import com.xiao.service.MenuService;
import com.xiao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allRole);
        return result;
    }


    @Autowired
    private MenuService menuService;
    //查询所有信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        //-1 表示查询所有菜单数据
        List<Menu> subMenuList = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("subMenuList",subMenuList);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

   // 查询角色关联菜单列表ID
   @RequestMapping("/findMenuByRoleId")
   public ResponseResult findMenuByRoleId(@RequestParam Integer roleId){
       List<String> menuByRoleId = roleService.findMenuByRoleId(roleId);
       ResponseResult result = new ResponseResult(true, 200, "响应成功", menuByRoleId);
       return result;
   }

   //用户关联菜单 {roleId: 4, menuIdList: [19, 20, 7, 8, 9, 15, 16, 17, 18]}
   @RequestMapping("/RoleContextMenu")
   public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
       ResponseResult result = new ResponseResult(true, 200, "响应成功", " ");

       return result;
   }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(@RequestParam Integer id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", " ");

        return result;
    }
}
