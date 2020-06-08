package com.course.server.dto;

import com.github.pagehelper.PageInfo;

public class ChapterPageDto extends PageDto {

   private String courseId;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


}