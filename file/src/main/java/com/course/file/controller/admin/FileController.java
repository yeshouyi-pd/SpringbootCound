package com.course.file.controller.admin;

import com.course.server.dto.ResponseDto;
import com.course.server.service.TeacherService;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class FileController {
    private  static  final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Resource
    private TeacherService teacherService;
    /**
     * 上传
     * @return
     * //RequestParam 接受表单
     */
    @PostMapping("/upload")
    public ResponseDto upload(@RequestParam MultipartFile file) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        LOG.info("文件上传开始：{}",file);
        LOG.info(file.getOriginalFilename());
        LOG.info(String.valueOf(file.getSize()));
        //保存本地文件
        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String fullPath = "E:/file/teacher/"+key+"-"+fileName;
        File dest = new File(fullPath);//生产目标路径
        file.transferTo(dest);//将文件接入目标路径
        LOG.info(dest.getAbsolutePath());
        return responseDto;
    }
}
