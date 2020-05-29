package com.course.business.controller;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BusinessController {

    @Autowired
    private ChapterService chapterService;


    /**
     * 查询
     * @return
     */
    @RequestMapping("/query")
    public List<ChapterDto> query(){
        ChapterExample example = new ChapterExample();
        ChapterExample.Criteria ca = example.createCriteria();
        List<Chapter> data = chapterService.list(example);
        List<ChapterDto> list = new ArrayList<ChapterDto>();
        if (null != data && data.size() >0){
            for (Chapter vo : data){
                ChapterDto chapterDto = new ChapterDto();
                BeanUtils.copyProperties(vo,chapterDto);
                list.add(chapterDto);

            }
        }
        return list;
    }
}
