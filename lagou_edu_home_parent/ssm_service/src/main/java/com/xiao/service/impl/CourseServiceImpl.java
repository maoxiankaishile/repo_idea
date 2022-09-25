package com.xiao.service.impl;


import com.xiao.dao.CourseMapper;
import com.xiao.domain.Course;
import com.xiao.domain.CourseVO;
import com.xiao.domain.Teacher;
import com.xiao.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByConditioin(CourseVO courseVO) {
        List<Course> courseList = courseMapper.findCourseByConditioin(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();

        //记住BeanUtils一定要是 org.apache.commons.beanutils.BeanUtils; 包下的
        //否则保存不了数据
        BeanUtils.copyProperties(course,courseVO);


        //补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        /*获取新插入数据的ID  如果想要同时与课程同时存到数据库就要先获取
        *                   先存入到数据库的课程id然后根据课程id存入
        *                   讲师的信息
        * */
        int id = course.getId();

        //封装讲师的信息
        Teacher teacher = new Teacher();
        //将讲师的信息也封装到courseVo中
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        //调取方法保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(int id) {
       return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        courseMapper.updateCourse(course);

        //封装教师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        //封装数据
        Course course = new Course();
        course.setUpdateTime(new Date());
        course.setId(id);
        course.setStatus(status);

        //调用dao层
        courseMapper.updateCourseStatus(course);
    }
}
