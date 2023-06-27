package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @date 2022/8/5 18:29
 * @Description: 后台公告concotroller
 */
@RestController
@RequestMapping("announce")
@Slf4j
public class NoticeController {


    @Resource
    private AnnouncementService announcementService;

    /**
     * 公告数据的全查询的 controller
     *
     * @param currentPag 当前页的页数
     * @param pageSize   页容量
     * @return 返回 Res 的数据对象
     */
    @GetMapping()
    public RespResult selectAllAnnounce(@RequestParam("page") Integer currentPag,
                                        @RequestParam("limit") Integer pageSize) {
        return announcementService.selectList(currentPag, pageSize);
    }

    @DeleteMapping()
    public RespResult deleteAllAnnounceById(@RequestParam("id") int id) {
        return announcementService.deleteAllAnnounceById(id);
    }

    @PostMapping()
    public RespResult addAnnounce(@RequestParam("announcement") String announcement,
                                  @RequestParam("file") List<MultipartFile> files,
                                  HttpServletRequest request) {
        log.info("announcement={}",announcement);
        log.info("file={}",files.size());
        return announcementService.insertAnnouncement(files,announcement,request);
    }
}
