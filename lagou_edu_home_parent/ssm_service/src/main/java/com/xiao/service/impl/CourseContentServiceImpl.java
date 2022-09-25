package com.xiao.service.impl;

import com.xiao.dao.CourseContentMapper;
import com.xiao.domain.Course;
import com.xiao.domain.CourseLesson;
import com.xiao.domain.CourseSection;
import com.xiao.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {


    /*注意CourseContentMapper注入对象千万年不要拿错
    * 如果拿错成CourseContentService就会出现死循环
    * 会报出java.lang.StackOverflowError(堆栈异常)
    * 报出这样的错误主要有两个：
    *   1.系统的空间确实不够
    *   2.程序出现了死循环（例如：一直递归的调用自己）
    *   第二个出现的概率比较大
    * */
    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> sectionList = contentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        Course course = contentMapper.findCourseByCourseId(courseId);
        return course;

    }

    @Override
    public void saveSection(CourseSection section) {
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        contentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setUpdateTime(date);
        contentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());

        contentMapper.updateSectionStatus(section);

    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        //补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        contentMapper.saveLesson(lesson);
    }

}
