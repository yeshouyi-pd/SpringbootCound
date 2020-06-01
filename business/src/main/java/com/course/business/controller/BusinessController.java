package com.course.business.controller;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class BusinessController {

    @Autowired
    private ChapterService chapterService;


    /**
     * 查询
     * @return
     */
    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto){
        chapterService.list(pageDto);
        return pageDto;
    }
    @RequestMapping("/save")
    public ChapterDto  save(@RequestBody ChapterDto chapterDto){
        chapterService.save(chapterDto);
        return chapterDto;
    }
}
