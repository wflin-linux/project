package com.ccww.aiotlab.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ccww.aiotlab.entity.Announcement;
import com.ccww.aiotlab.entity.vo.AnnouncementVo;

import java.util.List;

/**
 * @Entity com.ccww.aiotlab.entity.Announcement
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    /**
     * discription 分页查询全部的公告数据
     * @param page 分页对象
     * @return 返回 vo 的数据列表
     */
    List<AnnouncementVo> selectAllPage(IPage<AnnouncementVo> page);
}




