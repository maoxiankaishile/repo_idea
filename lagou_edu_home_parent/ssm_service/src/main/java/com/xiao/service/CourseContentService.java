package com.xiao.service;

import com.xiao.domain.Course;
import com.xiao.domain.CourseLesson;
import com.xiao.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据课程ID查询章节与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 根据Id进行课时的数据回显
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 保存章节信息
     * */
    public void saveSection(CourseSection section);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(Integer id, Integer status);

    /**
     * 保存课时
     */
    public void saveLesson(CourseLesson lesson);
}
