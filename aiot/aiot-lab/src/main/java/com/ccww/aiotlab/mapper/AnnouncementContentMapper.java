package com.ccww.aiotlab.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccww.aiotlab.entity.AnnouncementContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.ccww.aiotlab.entity.AnnouncementContent
 */
public interface AnnouncementContentMapper extends BaseMapper<AnnouncementContent> {
    List<AnnouncementContent> selectAllByAnnounceId(@Param("announceId") Integer announceId);
}




