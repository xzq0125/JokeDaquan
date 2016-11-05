package com.jun.jokedaquan.request;


import com.google.gson.reflect.TypeToken;
import com.jun.jokedaquan.entity.ResponseDtoEntity;
import com.jun.jokedaquan.entity.gif.GifDto;
import com.jun.jokedaquan.entity.gif.TextDto;

/**
 * 请求工厂
 * Created by Tse on 2016/10/19.
 */
public class RequestFactory {

    public static final String ADDRESS_GET_GIF = "/341-3";//动图地址
    public static final String ADDRESS_GET_PIC = "/341-2";//图片地址
    public static final String ADDRESS_GET_TEXT = "/341-1";//文本地址


    /**
     * 获取动图笑话
     *
     * @param maxResult 每页记录数，1到50
     * @param page      第几页
     * @return 请求
     */
    public static RequestHelper getGifJoke(int maxResult, int page) {
        RequestUrlBuilder urlMaker = new RequestUrlBuilder(ADDRESS_GET_GIF);
        urlMaker.addUrlData("maxResult", maxResult);
        urlMaker.addUrlData("page", page);
        return new RequestHelper(urlMaker, new TypeToken<ResponseDtoEntity<GifDto>>() {
        });
    }

    /**
     * 获取图片笑话
     *
     * @param maxResult 每页最大记录数。其值为1至50
     * @param page      第几页
     * @return 请求
     */
    public static RequestHelper getPicJoke(int maxResult, int page) {
        RequestUrlBuilder urlMaker = new RequestUrlBuilder(ADDRESS_GET_PIC);
        //从这个时间以来最新的笑话.  格式：yyyy-MM-dd
        urlMaker.addUrlData("time", "2011-11-11");
        urlMaker.addUrlData("maxResult", maxResult);
        urlMaker.addUrlData("page", page);
        return new RequestHelper(urlMaker, new TypeToken<ResponseDtoEntity<GifDto>>() {
        });
    }

    /**
     * 获取文本笑话
     *
     * @param maxResult 每页最大记录数。其值为1至50
     * @param page      第几页
     * @return 请求
     */
    public static RequestHelper getTextJoke(int maxResult, int page) {
        RequestUrlBuilder urlMaker = new RequestUrlBuilder(ADDRESS_GET_TEXT);
        //从这个时间以来最新的笑话.  格式：yyyy-MM-dd
        urlMaker.addUrlData("time", "2011-11-11");
        urlMaker.addUrlData("maxResult", maxResult);
        urlMaker.addUrlData("page", page);
        return new RequestHelper(urlMaker, new TypeToken<ResponseDtoEntity<TextDto>>() {
        });
    }

}
