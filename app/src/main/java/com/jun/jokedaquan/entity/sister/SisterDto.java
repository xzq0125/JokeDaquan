package com.jun.jokedaquan.entity.sister;

import java.util.List;

/**
 * SisterDto
 * Created by sean on 2016/11/10.
 */

public class SisterDto {

    public int ret_code;

    public PageDto pagebean;

    public static class PageDto {
        public List<SisterContentDto> contentlist;
        public int allPages;
        public int currentPage;
        public int maxResult;
        public int allNum;
    }

    public static class SisterContentDto {
        public String voicetime;//声音时长
        public String voiceuri;//
        public String voicelength;//

        public String videotime;//视频时长
        public String video_uri;//视频url

        public String width;//
        public String height;//

        public String image0;//
        public String image1;//
        public String image2;//
        public String image3;//3号图

        public String weixin_url;//微信链接地址
        public String profile_image;//用户头像
        public String type;//类型
        public String love;//点赞的数量
        public String hate;//点踩的数量
        public String text;//段子正文
        public String name;//
        public String id;//
        public String create_time;//创建时间

    }

}
