package com.xiao.service;

import com.github.pagehelper.PageInfo;
import com.xiao.domain.Course;
import com.xiao.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

 /**
  * 多条件联表查询
  * */

 public List<Course> findCourseByConditioin(CourseVO courseVO);

 /**
  * 保存课程信息
  * */
 public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;


 /**
  * 根据id获取课程信息
  */
 public CourseVO findCourseById(int id);

/**
 * 修改课程信息
 * */
 public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;


 /**
  * 修改课程状态
  * */
 public void updateCourseStatus(int id,int status);
}
