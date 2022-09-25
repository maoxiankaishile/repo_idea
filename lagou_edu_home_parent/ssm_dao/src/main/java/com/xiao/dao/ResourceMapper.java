package com.xiao.dao;

import com.xiao.domain.Resource;
import com.xiao.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {


    /**
     * 资源列表及多条件组合查询
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);

}
