package com.pd.server.service;

import com.pd.server.domain.Section;
import com.pd.server.domain.SectionExample;
import com.pd.server.dto.SectionDto;
import com.pd.server.dto.SectionPageDto;
import com.pd.server.mapper.SectionMapper;
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
public class SectionService {

@Resource
private SectionMapper sectionMapper;

    @Resource
    private CourseService courseService;

/**
* 列表查询
*/
public void list(SectionPageDto sectionPageDto) {
    PageHelper.startPage(sectionPageDto.getPage(), sectionPageDto.getSize());
    SectionExample sectionExample = new SectionExample();
    SectionExample.Criteria ca = sectionExample.createCriteria();
    if(!StringUtils.isEmpty(sectionPageDto.getChapterId())){
        ca.andChapterIdEqualTo(sectionPageDto.getChapterId());
    }
    if(!StringUtils.isEmpty(sectionPageDto.getCourseId())){
        ca.andCourseIdEqualTo(sectionPageDto.getCourseId());
    }
    sectionExample.setOrderByClause("sort asc");
    List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
    PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
    sectionPageDto.setTotal(pageInfo.getTotal());
    List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
    sectionPageDto.setList(sectionDtoList);
}

    /**
    * 保存，id有值时更新，无值时新增
    */
    //@Transactional(rollbackFor = Exception.class) 支持Exception回滚
    @Transactional
    public void save(SectionDto sectionDto) {
    Section section = CopyUtil.copy(sectionDto, Section.class);
    if (StringUtils.isEmpty(sectionDto.getId())) {
    this.insert(section);
    } else {
    this.update(section);
    }
        courseService.updateTime(sectionDto.getCourseId());
    }

    /**
    * 新增
    */
    private void insert(Section section) {
            Date now = new Date();
            section.setCreatedAt(now);
            section.setUpdatedAt(now);
    section.setId(UuidUtil.getShortUuid());
    sectionMapper.insert(section);
    }

    /**
    * 更新
    */
    private void update(Section section) {
            section.setUpdatedAt(new Date());
    sectionMapper.updateByPrimaryKey(section);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    sectionMapper.deleteByPrimaryKey(id);
    }
    }
