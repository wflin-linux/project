package com.ccww.aiotlab.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccww.aiotlab.entity.AnnouncementImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.ccww.aiotlab.entity.AnnouncementImage
 */
public interface AnnouncementImageMapper extends BaseMapper<AnnouncementImage> {
    List<AnnouncementImage> selectAllByAnnounceId(@Param("announceId") Integer announceId);
}




