package com.pd.business.controller.admin;

import com.pd.server.dto.ChapterDto;
import com.pd.server.dto.ChapterPageDto;
import com.pd.server.dto.ResponseDto;
import com.pd.server.service.ChapterService;
import com.pd.server.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/chapter")
public class BusinessController {

    @Autowired
    private ChapterService chapterService;
    public static final String BUSINESS_NAME = "大章";

    /**
     * 查询
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto){
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(), "课程ID");
        chapterService.list(chapterPageDto);
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto  save(@RequestBody ChapterDto chapterDto){
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }
    @RequestMapping("/delete/{id}")
    public ResponseDto  delete(@PathVariable String  id){
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }



}
