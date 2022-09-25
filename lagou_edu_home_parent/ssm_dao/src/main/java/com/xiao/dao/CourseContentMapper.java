package com.xiao.dao;

import com.xiao.domain.Course;
import com.xiao.domain.CourseLesson;
import com.xiao.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /**
     *查询课时下的章节与课时
     **/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 回显回应的课时信息
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 保存章节
     */
    public void saveSection(CourseSection section);

    /**
     * 修改章节信息
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     */
    public void updateSectionStatus(CourseSection section);

    /**
     * 保存课时
     */
    public void saveLesson(CourseLesson lesson);
}
