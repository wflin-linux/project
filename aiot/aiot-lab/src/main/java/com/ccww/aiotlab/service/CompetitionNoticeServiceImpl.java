package com.ccww.aiotlab.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.common.utils.PathUtils;
import com.ccww.aiotlab.common.utils.UserThreadLocalUtil;
import com.ccww.aiotlab.entity.Competition;
import com.ccww.aiotlab.entity.CompetitionFile;
import com.ccww.aiotlab.entity.CompetitionImage;
import com.ccww.aiotlab.entity.User;
import com.ccww.aiotlab.entity.vo.CompetitionBackVo;
import com.ccww.aiotlab.entity.vo.CompetitionVo;
import com.ccww.aiotlab.mapper.CompetitionFileMapper;
import com.ccww.aiotlab.mapper.CompetitionImageMapper;
import com.ccww.aiotlab.mapper.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author end
 * @date 2022年08月27日 15:58
 */
@Service
public class CompetitionNoticeServiceImpl {

    @Value("${upload.imgType}")
    private String imgType;

    @Value("${upload.fileType}")
    private String fileType;

    @Value("${upload.imgPath}")
    private String imgPath;

    @Value("${upload.filePath}")
    private String filePath;

    @Value("${upload.pathPatterns}")
    private String pathPatterns;

    @Autowired
    private CompetitionMapper competitionMapper;

    @Autowired
    private CompetitionImageMapper competitionImageMapper;

    @Autowired
    private CompetitionFileMapper competitionFileMapper;

    public RespResult upload(MultipartFile multipartFile, HttpServletRequest request, String type) {
        if (multipartFile.isEmpty()) {
            return RespResultUtil.failure("文件错误");
        }
        // 准备把上传图片保存在upload目录下，还有子目录 upload/(img | file)/时间/文件
        // 获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        // 2099/2/3/wqeqeqe.png
        String realPath;
        if (imgType.equals(type)) {
            realPath = PathUtils.generateFilePath(type, imgPath, originalFilename);
        } else {
            // 查询数据库中是否存在改文件
            CompetitionFile file = competitionFileMapper.selectOne(new QueryWrapper<CompetitionFile>().lambda().eq(CompetitionFile::getRealName, originalFilename));
            if (!Objects.isNull(file)) {
                return RespResultUtil.failure("文件已存在");
            }
            realPath = PathUtils.generateFilePath(type, filePath, originalFilename);
        }

        try {
            multipartFile.transferTo(new File(realPath));
            // 回调地址
            if (fileType.equals(type)) {
                realPath = realPath.substring(0, realPath.indexOf(originalFilename)) + URLEncoder.encode(originalFilename, "utf-8");
            }
            String requestUrl = request.getRequestURL().toString();
            requestUrl = requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
            String url = new StringBuilder().append(requestUrl).append("/").append(realPath.substring(realPath.indexOf(pathPatterns))).toString();
            Map<String, String> map = new HashMap<>();
            map.put("url", url);
            if (imgType.equals(type)) {
                CompetitionImage competitionImage = new CompetitionImage();
                competitionImage.setImgPath(url);
                competitionImageMapper.insert(competitionImage);
            } else {
                CompetitionFile competitionFile = new CompetitionFile();
                competitionFile.setFilePath(url);
                competitionFile.setRealName(originalFilename);
                competitionFileMapper.insert(competitionFile);
                CompetitionFile file = competitionFileMapper.selectOne(new QueryWrapper<CompetitionFile>().lambda().eq(CompetitionFile::getRealName, originalFilename));
                map.put("id", file.getId().toString());
            }
            return RespResultUtil.success("上传成功", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespResultUtil.failure("上传失败");
    }


    public RespResult getCompetitionList(Integer page, Integer limit) {
        Integer count = competitionMapper.selectCount(new QueryWrapper<>());

        List<CompetitionBackVo> list = competitionMapper.selectByAll((page - 1) * limit, limit);

        return RespResultUtil.success(list).setCode(0).setCount(Long.valueOf(count));
    }

    @Transactional
    public RespResult delete(Integer id) {
        competitionMapper.deleteById(id);
        competitionFileMapper.delete(new QueryWrapper<CompetitionFile>().lambda().eq(CompetitionFile::getCompetitionId, id));
        return RespResultUtil.success("删除成功");
    }

    @Transactional
    public RespResult saveOrUpdate(CompetitionVo competitionVo) {
        Competition selectOne;
        User user = UserThreadLocalUtil.get();
        Competition competition = new Competition();
        competition.setCreator(user.getId());
        competition.setTitle(competitionVo.getTitle());
        competition.setContent(competitionVo.getContent());
        competition.setCategoryName(competitionVo.getName());

        if (!Objects.isNull(competitionVo.getId())) {
            competition.setId(competitionVo.getId());
            competitionMapper.updateById(competition);
        } else {
            selectOne = competitionMapper.selectOne(new QueryWrapper<Competition>().lambda().eq(Competition::getTitle, competitionVo.getTitle()));

            if (!Objects.isNull(selectOne)) {
                return RespResultUtil.failure("标题已存在");
            }
            competitionMapper.insert(competition);
        }

        if (competitionVo.getIds().length > 0) {
            selectOne = competitionMapper.selectOne(new QueryWrapper<Competition>().lambda().eq(Competition::getTitle, competitionVo.getTitle()));

            CompetitionFile competitionFile = new CompetitionFile();
            competitionFile.setCompetitionId(selectOne.getId());
            competitionFileMapper.update(competitionFile, new QueryWrapper<CompetitionFile>().lambda().in(CompetitionFile::getId, competitionVo.getIds()));
        }

        return RespResultUtil.success("发布成功");
    }

    public RespResult query(Integer id) {
        Competition competition = competitionMapper.selectOne(new QueryWrapper<Competition>().lambda().eq(Competition::getId, id));
        return RespResultUtil.success(competition);
    }

    public RespResult queryFile(Integer id) {
        List<CompetitionFile> competitionFiles = competitionFileMapper.selectList(new QueryWrapper<CompetitionFile>().lambda().eq(CompetitionFile::getCompetitionId, id));

        Map<String, Object> map = new HashMap<>();
        if (competitionFiles.isEmpty()) {
            map.put("flag", 0);
        } else {
            map.put("flag", 1);
            map.put("files", competitionFiles);
        }

        return RespResultUtil.success(map);
    }
}
