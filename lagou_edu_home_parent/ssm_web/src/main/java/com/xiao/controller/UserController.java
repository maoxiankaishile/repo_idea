package com.xiao.controller;

import com.github.pagehelper.PageInfo;
import com.xiao.domain.ResponseResult;
import com.xiao.domain.Role;
import com.xiao.domain.User;
import com.xiao.domain.UserVo;
import com.xiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);

        List list = pageInfo.getList();
        System.out.println(list);
        return result;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam Integer id, @RequestParam String status) {

        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id, status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
        return result;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        ResponseResult result = null;
        if (login != null) {
            //保存access_token
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());

            HttpSession session = request.getSession();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", login.getId());
            result = new ResponseResult(true, 200, "success", map);
            return result;
        } else {
            result = new ResponseResult(true, 200, "账户或者密码错误", null);
            return result;
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(@RequestParam Integer id) {
        List<Role> list = userService.findUserRelationRoleById(id);
        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", list);
        return result;

    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", null);
        return result;
    }

    //获取用户权限
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的 token
        String token = request.getHeader("Authorization");

        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //判断
        if (token.equals(access_token)){
            int user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else {
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }

    }
}

