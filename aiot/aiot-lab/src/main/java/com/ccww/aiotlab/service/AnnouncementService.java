package com.ccww.aiotlab.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.common.utils.PathUtils;
import com.ccww.aiotlab.common.utils.UserThreadLocalUtil;
import com.ccww.aiotlab.entity.Announcement;
import com.ccww.aiotlab.entity.AnnouncementContent;
import com.ccww.aiotlab.entity.AnnouncementImage;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.entity.vo.AnnouncementVo;
import com.ccww.aiotlab.mapper.AnnouncementContentMapper;
import com.ccww.aiotlab.mapper.AnnouncementImageMapper;
import com.ccww.aiotlab.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class AnnouncementService extends ServiceImpl<AnnouncementMapper, Announcement> {

    @Resource
    private AnnouncementMapper announcementMapper;
    @Resource
    private AnnouncementContentMapper announcementContentMapper;
    @Resource
    private AnnouncementImageMapper announcementImageMapper;
    @Resource
    private AnnouncementService announcementService;

    @Value("${upload.imgType}")
    private String imgType;
    @Value("${upload.imgPath}")
    private String imgPath;
    @Value("${upload.pathPatterns}")
    private String pathPatterns;

    private final static String[] imageType = {"png", "jpg", "jpeg", "gif"};

    public RespResult selectList(Integer currentPage, Integer pageSize) {
        IPage<AnnouncementVo> page = new Page<>(currentPage, pageSize);
        List<AnnouncementVo> announcementVos = announcementMapper.selectAllPage(page);
        return RespResultUtil.success(announcementVos).setCode(0).setMsg("当页已加载完毕").setCount(page.getTotal());
    }
    @Transactional
    public RespResult deleteAllAnnounceById(int id) {
        /**
         * 通过 announcement_id 查询到对应的该公告的信息列表
         */
        try {
            List<AnnouncementContent> announcementContents =
                    announcementContentMapper.selectAllByAnnounceId(id);
            for (AnnouncementContent announcementContent :
                    announcementContents) {
                announcementContentMapper.deleteById(announcementContent.getId());
            }

            List<AnnouncementImage> announcementImages = announcementImageMapper.selectAllByAnnounceId(id);
            for (AnnouncementImage announcementImage :
                    announcementImages) {
                announcementImageMapper.deleteById(announcementImage.getId());
            }
            announcementMapper.deleteById(id);
        } catch (Exception e) {
            return RespResultUtil.success("删除失败");
        }
        return RespResultUtil.success("删除成功");
    }

    @Transactional
    public RespResult insertAnnouncement(List<MultipartFile> files, String Content,HttpServletRequest request) {
        User user = UserThreadLocalUtil.get();
        Announcement announcement = new Announcement();
        announcement.setCuId(user.getId());
        /**
         * 插入公告
         */
        announcementMapper.insert(announcement);

        /**
         * 获得插入公告 id
         */
        Integer announcementId = announcement.getId();

        /**
         * 添加 AnnouncementContent
         */
        AnnouncementContent announcementContent = new AnnouncementContent();
        announcementContent.setAnnounceId(announcementId);
        announcementContent.setContent(Content);
        announcementContentMapper.insert(announcementContent);
        /**
         * 添加 AnnouncementImage
         */
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                return RespResultUtil.failure("文件错误");
            }
            announcementService.upload(file, announcementId,request);
        }
        return RespResultUtil.success("添加公告成功");
    }

    public void upload(MultipartFile multipartFile,Integer announceId,HttpServletRequest request) {
        // 准备把上传图片保存在upload目录下，还有子目录 upload/(img | file)/时间/文件
        // 获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        // 2099/2/3/wqeqeqe.png
        String realPath = PathUtils.generateFilePath(imgType, imgPath, originalFilename);
        try {
            multipartFile.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestUrl = request.getRequestURL().toString();
        requestUrl = requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
        System.out.println(requestUrl);
        String url = new StringBuilder().append("/").append(realPath.substring(realPath.indexOf(pathPatterns))).toString();
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        AnnouncementImage announcementImage = new AnnouncementImage();
        announcementImage.setAnnounceId(announceId);
        announcementImage.setImgPath(url);
        announcementImageMapper.insert(announcementImage);
    }
}
