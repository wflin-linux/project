package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.entity.vo.CompetitionVo;
import com.ccww.aiotlab.service.CompetitionNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author end
 * @date 2022年08月27日 15:56
 */
@RestController
@RequestMapping("/competition")
public class CompetitionNoticeController {

    @Autowired
    private CompetitionNoticeServiceImpl competitionNoticeService;

    @Value("${upload.imgType}")
    private String imgType;

    @Value("${upload.fileType}")
    private String fileType;

    private final static String[] imageType = {"png", "jpg", "jpeg", "gif"};

    @PostMapping("upload/img")
    public RespResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!isImage(file)) {
            return RespResultUtil.failure("文件格式错误");
        }
        return competitionNoticeService.upload(file, request, imgType);
    }

    // 解决Editormd文件上传
    @PostMapping("upload/editorUpload")
    public Map<String, Object> editorUpload(
            @RequestParam(value = "editormd-image-file")MultipartFile img,
            HttpServletRequest request) throws JSONException {
        if (!isImage(img)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("success", 0);
            map.put("message", "文件格式错误");
            return map;
        }
        RespResult result = competitionNoticeService.upload(img, request, imgType);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("success", result.getCode() == 200 ? 1 : 0);
        map1.put("message", result.getMsg());
        if (!Objects.isNull(result.getData())) {
            Map map = (Map) result.getData();
            map1.put("url", map.get("url"));
        }

        return map1;
    }

    @PostMapping("upload/file")
    public RespResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (isImage(file)) {
            return RespResultUtil.failure("文件格式错误");
        }
        return competitionNoticeService.upload(file, request, fileType);
    }

    @GetMapping("getCompetitionList")
    public RespResult getCompetitionList(Integer page, Integer limit){
        return competitionNoticeService.getCompetitionList(page, limit);
    }

    @PostMapping("saveOrUpdate")
    public RespResult saveOrUpdate(@RequestBody CompetitionVo competitionVo) {
        return competitionNoticeService.saveOrUpdate(competitionVo);
    }

    @DeleteMapping("/delete/{id}")
    public RespResult delete(@PathVariable("id") Integer id){
        return competitionNoticeService.delete(id);
    }

    @GetMapping("query")
    public RespResult query(@RequestParam("id") Integer id) {
        return competitionNoticeService.query(id);
    }

    @GetMapping("queryFile")
    public RespResult queryFile(@RequestParam("id") Integer id) {
        return competitionNoticeService.queryFile(id);
    }

    private boolean isImage(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index + 1);
        return Arrays.asList(imageType).contains(fileType);
    }
}
