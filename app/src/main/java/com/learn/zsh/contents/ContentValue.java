package com.learn.zsh.contents;

import com.learn.zsh.internetlearn.service.Background2Service;
import com.learn.zsh.internetlearn.service.BackgroundService;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class ContentValue {
    public static final String HTTPCLINETACTION = "zsh.debug.action.HTTPCLIENTDEBUG_PC";
    public static final String IMAGEDOWNACTION = "zsh.debug.action.IMAGEDOWNDEBUG_PC";
    public static final String USERDEFINDACTION = "zsh.debug.action.USERDEFINDVIEW_PC";
    public static final String SERVICEDEBUGACTION = "zsh.debug.action.SERVICEPAGE_PC";
    public static final String FRAGMENTDEBUGACTION = "zsh.debug.action.FRAGMENT_PC";
    public static final String DBG_WEB_BAIDU = "https://www.baidu.com";
    public static final String DBG_WEB_TAOBAO_IP = "http://ip.taobao.com/ipSearch.html";
    public static final String DBG_WEB_BAUDU_IMAGE = "https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fpic21.photophoto.cn%2F20111021%2F0034034817592972_b.jpg&thumburl=https%3A%2F%2Fss1.bdstatic.com%2F70cFuXSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D2023318863%2C2589403806%26fm%3D27%26gp%3D0.jpg";
    public static final String WEB_HEADR = "https://";
    public static final String CONTENTCHARSET = "UTF-8";
    public static final String DEFAULT_TIPS = "Get nothing.";
    public static final int CONN_SUCCES = 200;
    public static final long IMAGE_DISKCACHE_SIZE = 1024 * 1024 * 50;
    public static final int DISK_CACHE_INDEX = 0;
    public static final int IO_BUFFER_SIZE = 1024 * 8;


    public static final Class BACKGROUND1_SERVICE = BackgroundService.class;
    public static final Class BACKGROUND2_SERVICE = Background2Service.class;

    public static final int TOAST_SHOW_TIME = 600;
    public static final int REQUEST_TIMEOUT = 1500;
    public static final int CONN_TIMEOUT = 1500;
    public static final String HEAD_KEY_NAME = "Connection";
    public static final String HEAD_VALUE_NAME = "Keep-Alive";
    public static final String IP_FOR_PAIRNAME = "ip";
    public static final String IP_OF_PAIRVALUE = "112.64.60.227";

    public static final String[] IMAGE_URL_LIST = new String[]{
            "http://img03.sogoucdn.com/app/a/07/e023d74b837f1504e450a9148bc9e62d",
            "http://img3.tuniucdn.com/images/2011-03-29/L/LFXLzoSGG9g753SH.jpg",
            "http://pic19.nipic.com/20120324/3484432_092618805000_2.jpg",
            "http://image.tianjimedia.com/uploadImages/2011/306/MOH58845COC4.jpg",
            "http://pic23.nipic.com/20120824/8218020_193654254129_2.jpg",
            "http://pic12.nipic.com/20110223/2709576_111836168000_2.jpg"
    };
}
