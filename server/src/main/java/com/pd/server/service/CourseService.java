package com.pd.server.service;

import com.pd.server.domain.Course;
import com.pd.server.domain.CourseContent;
import com.pd.server.domain.CourseExample;
import com.pd.server.dto.CourseContentDto;
import com.pd.server.dto.CourseDto;
import com.pd.server.dto.PageDto;
import com.pd.server.dto.SortDto;
import com.pd.server.mapper.CourseContentMapper;
import com.pd.server.mapper.CourseMapper;
import com.pd.server.mapper.my.MyCourseMapper;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class CourseService {

@Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;
    @Resource
    private CourseCategoryService courseCategoryService;
    @Resource
    private CourseContentMapper courseContentMapper;




/**
* 列表查询
*/
public void list(PageDto pageDto) {
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
List<Course> courseList = courseMapper.selectByExample(courseExample);
PageInfo<Course> pageInfo = new PageInfo<>(courseList);
pageDto.setTotal(pageInfo.getTotal());
List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
    pageDto.setList(courseDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */

    public void save(CourseDto courseDto) {
    Course course = CopyUtil.copy(courseDto, Course.class);
    if (StringUtils.isEmpty(courseDto.getId())) {
    this.insert(course);
    } else {
    this.update(course);
    }
        courseCategoryService.saveBatch(course.getId(),courseDto.getCategoryDtos());
    }

    /**
    * 新增
    */
    private void insert(Course course) {
            Date now = new Date();
            course.setCreatedAt(now);
            course.setUpdatedAt(now);
    course.setId(UuidUtil.getShortUuid());
    courseMapper.insert(course);
    }

    /**
    * 更新
    */
    private void update(Course course) {
            course.setUpdatedAt(new Date());
    courseMapper.updateByPrimaryKey(course);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    courseMapper.deleteByPrimaryKey(id);
    }

    public void  updateTime(String courseId){
        myCourseMapper.updateTime(courseId);
    }

    /**
     * 查询 课程内容
     */
    public CourseContentDto queryById(String id){
        CourseContent courseContent = courseContentMapper.selectByPrimaryKey(id);
        CourseContentDto   vo  =  CopyUtil.copy(courseContent ,CourseContentDto.class);

        return   vo;
    }

    /**
     * 添加课程内容
     */
    public void  insertContent(CourseContentDto courseContentDto){
        if(null != courseContentDto){
            CourseContent courseContent = new CourseContent();
            courseContent.setId(courseContentDto.getId());
            courseContent.setContent(courseContentDto.getContent());
            int falg = courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
            if(falg == 0){//添加失败 则新增
                courseContentMapper.insertSelective(courseContent);
            }

        }

    }

    /**
     * 排序
     * @param sortDto
     */
    @Transactional
    public void sort(SortDto sortDto) {
        // 修改当前记录的排序值
        myCourseMapper.updateSort(sortDto);

        // 如果排序值变大
        if (sortDto.getNewSort() > sortDto.getOldSort()) {
            myCourseMapper.moveSortsForward(sortDto);
        }

        // 如果排序值变小
        if (sortDto.getNewSort() < sortDto.getOldSort()) {
            myCourseMapper.moveSortsBackward(sortDto);
        }
    }
}
