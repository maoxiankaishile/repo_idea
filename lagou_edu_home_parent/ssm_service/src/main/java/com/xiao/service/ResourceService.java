package com.xiao.service;


import com.github.pagehelper.PageInfo;
import com.xiao.domain.Resource;
import com.xiao.domain.ResourceVo;

public interface ResourceService {

    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);

}
