package com.jun.jokedaquan.entity.gif;

import java.util.List;

/**
 * TextDto
 * Created by Tse on 2016/11/5.
 */

public class TextDto {

    public int allPages;
    public int ret_code;
    public int currentPage;
    public int allNum;
    public int maxResult;

    public List<TextDto.ContentlistBean> contentlist;

    public static class ContentlistBean {
        public String id;
        public String title;
        public String text;
        public int type;
        public String ct;
    }
}
