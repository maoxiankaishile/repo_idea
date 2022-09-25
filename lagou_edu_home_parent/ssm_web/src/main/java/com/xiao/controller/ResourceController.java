package com.xiao.controller;

import com.github.pagehelper.PageInfo;
import com.xiao.domain.Resource;
import com.xiao.domain.ResourceVo;
import com.xiao.domain.ResponseResult;
import com.xiao.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){
        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功",allResource);
        return result;
    }


}
