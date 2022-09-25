package com.xiao.dao;

import com.xiao.domain.Course;
import com.xiao.domain.CourseVO;
import com.xiao.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 多条件课程查询
     */
    public List<Course> findCourseByConditioin(CourseVO courseVO);

    /**
     *保存课程信息
     */
    public int saveCourse(Course course);


    /**
     * 保存讲师信息
     * */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据id 获取课程信息
     * @return
     */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     * */
    public void updateCourse(Course course);

    /**
     * 修改讲师信息
     * */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改状态信息
     */
    public void updateCourseStatus(Course course);
}
