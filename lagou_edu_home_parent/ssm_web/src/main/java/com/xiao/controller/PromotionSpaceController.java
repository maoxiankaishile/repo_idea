package com.xiao.controller;


import com.xiao.domain.PromotionSpace;
import com.xiao.domain.ResponseResult;
import com.xiao.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }


    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId() == null){
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
            return result;
        }else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id){
        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpaceById);
        return result;
    }

}
