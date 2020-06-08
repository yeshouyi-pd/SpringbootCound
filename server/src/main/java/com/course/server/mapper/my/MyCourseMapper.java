package com.course.server.mapper.my;

import com.course.server.domain.Chapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCourseMapper {

    int updateTime(@Param("courseId") String courseId);
}
