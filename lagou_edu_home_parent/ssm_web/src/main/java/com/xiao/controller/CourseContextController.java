package com.xiao.controller;

import com.xiao.domain.*;
import com.xiao.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")

public class CourseContextController {

    @Autowired
    private CourseContentService contentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@RequestParam Integer courseId){
        List<CourseSection> sectionList = contentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true,200,"响应成功",sectionList);
        return result ;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = contentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }


/*
*
*  @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
* 而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，
* 一般都用POST方式进行提交。在后端的同一个接收方法里，
* @RequestBody与@RequestParam()可以同时使用，@RequestBody最多只能有一个，而@RequestParam()可以有多个。
*
* 简单一句话就是识别前端传递给后端的json字符串中的数据的
* */
@RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){

        if(section.getId() == null){
            contentService.saveSection(section);
            ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
            return result;
        }else {
            contentService.updateSection(section);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam Integer id, @RequestParam Integer status){
    contentService.updateSectionStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson lesson){
    contentService.saveLesson(lesson);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }




}
