package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            for (Chapter vo : listChapter) {
                ChapterDto chapterDto = new ChapterDto();
                BeanUtils.copyProperties(vo, chapterDto);
                list.add(chapterDto);

            }
            pageDto.setList(list);

        }
        return  pageDto;
    }
}


