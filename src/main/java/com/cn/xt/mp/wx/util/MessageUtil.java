package com.cn.xt.mp.wx.util;


import java.io.IOException;

import java.io.InputStream;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.cn.xt.mp.wx.entity.News;
import com.cn.xt.mp.wx.entity.NewsMessage;
import com.cn.xt.mp.wx.entity.TextMessage;
import org.dom4j.Document;

import org.dom4j.DocumentException;

import org.dom4j.Element;

import org.dom4j.io.SAXReader;


import com.thoughtworks.xstream.XStream;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 13:38
 **/
public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_EVENT = "event";

    public static final String MESSAGE_SUBSCRIBE = "subscribe";

    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

    public static final String MESSAGE_CLICK = "CLICK";

    public static final String MESSAGE_VIEW = "VIEW";

    public static final String MESSAGE_SCAN = "SCAN";
    public static final String MESSAGE_NEWS = "news";

    /**
     * 将XML转为MAP集合
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();

//从request对象中获取输入流

        InputStream ins = request.getInputStream();

//使用reader对象读取输入流,解析为XML文档

        Document doc = reader.read(ins);

//获取XML根元素

        Element root = doc.getRootElement();

//将根元素的所有节点，放入列表中

        List<Element> list = root.elements();

//遍历list对象，并保存到集合中

        for (Element element : list) {

            map.put(element.getName(), element.getText());

        }

        ins.close();

        return map;

    }

    /**
     * 将文本消息对象转成XML
     *
     * @param
     * @return
     */

    public static String textMessageToXml(TextMessage textMessage) {

        XStream xstream = new XStream();

//将xml的根节点替换成<xml>  默认为TextMessage的包名

        xstream.alias("xml", textMessage.getClass());

        return xstream.toXML(textMessage);

    }

    /**
     * 拼接关注主菜单
     */

    public static String menuText() {

        StringBuffer sb = new StringBuffer();

        sb.append("你想要什么，请选择:\n\n");

        sb.append("1、默默真帅。\n");

        sb.append("2、宝宝真漂亮。\n\n");
        sb.append("3、今晚吃什么鸭。\n\n");
        sb.append("4、还要什么饮料好呢。\n\n");
        sb.append("5、天宁帅炸天。\n\n");

        sb.append("回复？调出主菜单。\n\n");

        return sb.toString();

    }

    /**
     * 初始化回复消息
     */

    public static String initText(String toUSerName, String fromUserName, String content) {

        TextMessage text = new TextMessage();

        text.setFromUserName(toUSerName);

        text.setToUserName(fromUserName);

        text.setMsgType(MESSAGE_TEXT);

        text.setCreateTime(new Date().getTime());

        text.setContent(content);

        return MessageUtil.textMessageToXml(text);

    }

    /**
     * 将图文消息对象转成XML
     * @param
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        //将xml的根节点替换成<xml>  默认为NewsMessage的包名
        xstream.alias("xml", newsMessage.getClass());
        //同理，将每条图文消息News类的报名，替换为<item>标签
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }


    /**
     * 初始化图文消息
     */
    public static String initNewsMessage(String toUSerName,String fromUserName){
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();
        //组建一条图文↓ ↓ ↓
        News newsItem = new News();
        newsItem.setTitle("听说天宁很帅");
        newsItem.setDescription("听说天宁帅炸天，来看看吧");
        newsItem.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543994826&di=1ec43ccec6e24a64991b577f0a8cbba1&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.17qq.com%2Fimg_qqtouxiang%2F31238717.jpeg");
        newsItem.setUrl("www.51xt.com.cn");

//        newsItem.setTitle("听说天宁很帅");
//        newsItem.setDescription("来看看吧");
//        newsItem.setPicUrl("http://42052d4b.nat123.net/szt.jpg");
//        newsItem.setUrl("42052d4b.nat123.net");
        newsList.add(newsItem);

        //组装图文消息相关信息
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUSerName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        //调用newsMessageToXml将图文消息转化为XML结构并返回
        return MessageUtil.newsMessageToXml(newsMessage);
    }
}



