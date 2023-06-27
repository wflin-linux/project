package com.ccww.aiotlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccww.aiotlab.entity.Competition;
import com.ccww.aiotlab.entity.vo.CompetitionBackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author end
 * @date 2022年08月28日 14:00
 */
public interface CompetitionMapper extends BaseMapper<Competition> {

    List<CompetitionBackVo> selectByAll(@Param("page") Integer page, @Param("limit") Integer limit);
}
