package com.jun.jokedaquan.entity.musicpavilion;

import java.util.List;

/**
 * MusicPavilionDto
 * Created by Tse on 2016/10/19.
 */

public class MusicPavilionDto {

    public int ret_code;
    public PageBean pagebean;

    public static class PageBean {
        public List<Detail> songlist;
        public long total_song_num;
        public long ret_code;
        public long color;
        public long cur_song_num;
        public long comment_num;
        public long currentPage;
        public long song_begin;
        public long totalpage;
        public String top_name;

        public boolean isSongBegin() {
            return song_begin == 1;
        }

        public void stopPlay() {
            song_begin = 0;
        }

        public void startPlay() {
            song_begin = 1;
        }

    }

    public static class Detail {

        public String songname;
        public long seconds;
        public String albummid;
        public long songid;
        public long singerid;
        public String albumpic_big;
        public String albumpic_small;
        public String downUrl;
        public String url;
        public String singername;
        public long albumid;

    }

}
