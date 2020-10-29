package com.pd.business.controller.admin;

import com.pd.server.dto.SectionDto;
import com.pd.server.dto.ResponseDto;
import com.pd.server.dto.SectionPageDto;
import com.pd.server.service.SectionService;
import com.pd.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/section")
public class SectionController {

private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
public static final String BUSINESS_NAME = "小节";

@Resource
private SectionService sectionService;

/**
* 列表查询
*/
@PostMapping("/list")
public ResponseDto list(@RequestBody SectionPageDto sectionPageDto) {
ResponseDto responseDto = new ResponseDto();
sectionService.list(sectionPageDto);
responseDto.setContent(sectionPageDto);
return responseDto;
}

/**
* 保存，id有值时更新，无值时新增
*/
@PostMapping("/save")
public ResponseDto save(@RequestBody SectionDto sectionDto) {
// 保存校验
            ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
            ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

ResponseDto responseDto = new ResponseDto();
sectionService.save(sectionDto);
responseDto.setContent(sectionDto);
return responseDto;
}

/**
* 删除
*/
@DeleteMapping("/delete/{id}")
public ResponseDto delete(@PathVariable String id) {
ResponseDto responseDto = new ResponseDto();
sectionService.delete(id);
return responseDto;
}
}
