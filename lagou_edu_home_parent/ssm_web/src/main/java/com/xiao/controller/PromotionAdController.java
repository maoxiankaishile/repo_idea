package com.xiao.controller;

import com.github.pagehelper.PageInfo;
import com.xiao.domain.PromotionAd;
import com.xiao.domain.PromotionAdVo;
import com.xiao.domain.ResponseResult;
import com.xiao.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService adService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){
        PageInfo allAdByPage = adService.findAllPromotionAdByPage(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "分页查询成功", allAdByPage);
        return result;
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        ResponseResult result = null;
        try {
            //1.判断文件是否为空
            if (file.isEmpty()){
                //如果文件为空则直接报运行时异常
                throw  new RuntimeException();
            }
            //2.获取项目部署路径
            //E:\Tomcat_home\apache-tomcat-8.5.82\webapps\ssm_web
            String realPath = request.getServletContext().getRealPath("/");
            //对文件名进行截取
            //E:\Tomcat_home\apache-tomcat-8.5.82\webapps\
            //substring对文件名进行截取
            String webappsPath = realPath.substring(0,realPath.indexOf("ssm_web"));
            //3.获取源文件名
            // file.getOriginalFilename(); 获取源文件名的方法
            String fileName = file.getOriginalFilename();
            //4.设置新文件名
            String newFileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
            //5.上传文件
            String uploadPath = webappsPath + "upload\\";
            File filePath = new File(uploadPath, newFileName);
            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录："+filePath);
            }
            file.transferTo(filePath);
            //将文件名和文件路径返回
            Map<String,String> map = new HashMap<>();
            map.put("fileName",newFileName);
            map.put("filePath","http://localhost:8081/upload/"+newFileName);
            result= new ResponseResult(true, 200, "图片上传成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null){

            Date date = new Date();
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);

            adService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "保存查询成功", null);
            return result;
        }else{
            Date date = new Date();
            promotionAd.setUpdateTime(date);
            adService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "修改查询成功", null);
            return result;
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam Integer id){
        PromotionAd promotionAd = adService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true, 200, "回显成功", promotionAd);
        return result;
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam Integer id,@RequestParam Integer status){
        //执行修改操作
        if (status == 1){
            adService.updatePromotionAdStatus(id,status);
        }else {
            adService.updatePromotionAdStatus(id,0);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
}
