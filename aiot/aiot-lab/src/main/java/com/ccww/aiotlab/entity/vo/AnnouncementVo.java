package com.ccww.aiotlab.entity.vo;

import com.ccww.aiotlab.entity.Announcement;
import com.ccww.aiotlab.entity.AnnouncementContent;
import com.ccww.aiotlab.entity.AnnouncementImage;
import com.ccww.aiotlab.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2022/8/5 22:15
 * @Description: 重新封装Announcement
 */
@Data
public class AnnouncementVo extends Announcement {
    private User user;
    private AnnouncementContent announcementContent;
    private List<AnnouncementImage> announcementImageList;
}
