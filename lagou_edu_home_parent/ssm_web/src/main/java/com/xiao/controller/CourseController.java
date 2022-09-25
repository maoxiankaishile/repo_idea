package com.xiao.controller;


import com.xiao.domain.Course;
import com.xiao.domain.CourseVO;
import com.xiao.domain.ResponseResult;
import com.xiao.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 根据多条件查询信息
     * @param courseVO
     * @return
     */
    @RequestMapping("/findCourseByConditioin")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByConditioin(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return  result;
    }

    /**
     * 图片上传接口
     */

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
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

    /**
     * 保存信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        ResponseResult result = null;
        try {
            if (courseVO.getId() == null){
                courseService.saveCourseOrTeacher(courseVO);
                result = new ResponseResult(true, 200, "信息保存成功", courseVO);
            }else {
                courseService.updateCourseOrTeacher(courseVO);
                result = new ResponseResult(true,200,"信息修改成功",courseVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据id获取课程信息
     * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        CourseVO courseVo = courseService.findCourseById(id);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVo);
        return result;
    }


    /**
     * 修改课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id,@RequestParam int status){
        courseService.updateCourseStatus(id,status);

        //保存状态信息并返回
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改状态成功", map);
        return result;
    }
}
