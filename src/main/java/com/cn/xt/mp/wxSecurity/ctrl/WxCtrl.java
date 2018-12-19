package com.cn.xt.mp.wxSecurity.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.wxSecurity.wxentity.AccessToken;
import com.cn.xt.mp.wxSecurity.util.WXUtil;
import com.cn.xt.mp.wxSecurity.util.Message;
import com.cn.xt.mp.wxSecurity.util.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 11:25
 **/
@RestController
@RequestMapping("wx")
public class WxCtrl {

    @RequestMapping("/getImg")
    public String getImg(String account,HttpServletRequest request,HttpServletResponse resp) throws Exception{


        String temporaryQR = WXUtil.createPermanentQRCode(null, null);
        System.out.println(temporaryQR);
        //wechatImg.downloadFile(wechatImg.DOWNLOADIMG+ticket, "F://image//"+account+"//"+account+".jpg");
        //DownLoadUtii.downloadAmachment(wechatImg.DOWNLOADIMG+ticket, account+".jpg", request, resp);+
        return temporaryQR;
    }


    @RequestMapping("sendTemplateMessage")
    public Object sendTemplateMessage(String token,String openid) throws Exception {
       return  WXUtil.sendTemplateMessage(WXUtil.TOKEN,null);
    }
    @RequestMapping("getUserInfo")
    public String getUserInfo(String token,String openid) throws Exception {
       return   WXUtil.getUserByUserToken(token,openid).toJSONString();
    }


    @GetMapping("momo")
    public String momo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "momo";
    }

    @GetMapping("flushToken")
    public String flushToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                AccessToken tk = WXUtil.getAccessToken(request.getParameter("appId"));
                System.out.println(tk.getToken());
                System.out.println(tk.getExpiresIn());
                WXUtil.TOKEN=tk.getToken();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "success";
    }

    @PostMapping("access")
    public String doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");


        String message = "success";

        try {

//将request请求，传到Message工具类的转换方法中，返回接收到的Map对象

            Map<String, String> map = Message.xmlToMap(request);

//从集合中，获取XML各个节点的内容

            String ToUserName = map.get("ToUserName");

            String FromUserName = map.get("FromUserName");

            String CreateTime = map.get("CreateTime");

            String MsgType = map.get("MsgType");

            String Content = map.get("Content");

            String MsgId = map.get("MsgId");

            if (MsgType.equals(MessageUtil.MESSAGE_TEXT)) {//判断是否为文本消息类型

                if (Content.equals("1")) {

                    message = MessageUtil.initText(ToUserName, FromUserName, "对啊！我也是这么觉得！默默帅哭了！");

                } else if (Content.equals("2")) {

                    message = MessageUtil.initText(ToUserName, FromUserName,

                            "宝宝美哭了呢");

                }else if (Content.equals("5")) {
                    message = MessageUtil.initNewsMessage(ToUserName, FromUserName);
                    message = message;
                }else if (Content.equals("3")) {
                    String [] fruit ={"香蕉","柚子","葡萄","梨子","奇异果","橙子","橘子","桃子","布林","黑加仑"};
                    String [] meat ={"排骨","鸡翅","鸭子","小鸡炖蘑菇"};
                   String f = fruit[(int)(Math.random()*10)];
                    String m=  meat[(int)(Math.random()*4)];

                    message = " 今晚吃：\n\n"+f+"\n\n"+m+"\n\n";
                    message = MessageUtil.initText(ToUserName, FromUserName,

                            message);
                } else if (Content.equals("4")) {

                    String [] yl ={"橙子汁","菊花茶","雪碧","可乐","绿茶"};
                    String m=  yl[(int)(Math.random()*5)];

                    message = " 今晚喝：\n\n"+m;
                    message = MessageUtil.initText(ToUserName, FromUserName,

                            message);

                } else if (Content.equals("?") || Content.equals("？")) {

                    message = MessageUtil.initText(ToUserName, FromUserName, MessageUtil.menuText());
                } else if (Content.equals("我爱你") || Content.equals("爱你哦")) {

                    message = MessageUtil.initText(ToUserName, FromUserName, "默默超级爱默默家的宝宝的哦！");
                } else {
                    String ap = " 你发送的内容，默默不能识别哦\n\n";
                    message =ap+ MessageUtil.initText(ToUserName, FromUserName,

                            ap+MessageUtil.menuText());
                }


            } else if (MsgType.equals(MessageUtil.MESSAGE_EVENT)) {//判断是否为事件类型

                //从集合中，或许是哪一种事件传入

                String eventType = map.get("Event");

              //关注事件

                if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {

                    message = MessageUtil.initText(ToUserName, FromUserName,

                            MessageUtil.menuText());

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(message);


        return message;
    }
}
