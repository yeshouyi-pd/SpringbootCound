package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    public PageDto list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample example = new ChapterExample();
        ChapterExample.Criteria ca = example.createCriteria();
        List<Chapter> listChapter = chapterMapper.selectByExample(example);
        PageInfo<Chapter> pageInfo = new PageInfo<Chapter>(listChapter);
        pageDto.setTotal(pageInfo.getTotal());
        example.setOrderByClause(" id  desc ");
        List<ChapterDto> list = new ArrayList<ChapterDto>();
        if (null != listChapter && listChapter.size() > 0) {
            list =    CopyUtil.copyList(listChapter ,ChapterDto.class);
            pageDto.setList(list);

        }
        return  pageDto;
    }


    /**
     添加
     * @param chapterDto
     * @return
     */
    public  void  save(ChapterDto chapterDto){
        Chapter  chapter =  CopyUtil.copy(chapterDto,Chapter.class);
        if(StringUtils.isEmpty(chapterDto.getId())){
            chapter.setId(UuidUtil.getShortUuid());
            chapterMapper.insertSelective(chapter);
        }else{
            chapterMapper.updateByPrimaryKeySelective(chapter);
        }

    }

    public  void  delete (String id){
        chapterMapper.deleteByPrimaryKey(id);

    }


}


