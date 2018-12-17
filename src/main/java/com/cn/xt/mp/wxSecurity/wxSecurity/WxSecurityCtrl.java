package com.cn.xt.mp.wxSecurity.wxSecurity;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import com.cn.xt.mp.base.redis.util.RedisUtils;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.MD5Util;
import com.cn.xt.mp.mpModel.TbCompanyUser;
import com.cn.xt.mp.mpModel.WxSecurityPO;
import com.cn.xt.mp.mpModel.WxUserInfoPO;
import com.cn.xt.mp.service.ICompanyUserService;
import com.cn.xt.mp.service.IWxUserInfoService;
import com.cn.xt.mp.vo.CompanyUserVO;

import com.cn.xt.mp.service.IWxSecurityService;
import com.cn.xt.mp.wxSecurity.util.*;
import com.cn.xt.mp.wxSecurity.wxentity.js.JsapiTicket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 11:25
 **/
@Api(value = "WxSecurityCtrl",tags = "mp微信授权",description = "微信相关，包括获取微信用户信息")
@RestController
@RequestMapping("wxSecurity")
public class WxSecurityCtrl {


    @Autowired
    private  IWxSecurityService wxSecurityService;
    @Autowired
    private IWxUserInfoService wxUserInfoService;
    @Autowired
    private ICompanyUserService CompanyUserService;
    /**
     * 服务器配置,get方式
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @ApiIgnore()
    @GetMapping("access/{diy}")
    public String doGet(@PathVariable String diy, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String  tooken = security.getToken(); //开发者自行定义Tooken
        if (CheckUtil.checkSignature(tooken,signature, timestamp, nonce)) {
            //如果校验成功，将得到的随机字符串原路返回
            return echostr;
        }
        return null;
    }

    @ApiOperation(value = "显式获取用户信息",notes = "获取用户信息")
    //@ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query")
    @ApiImplicitParams({
            @ApiImplicitParam(name="diy",value="公众号唯一标识",dataType="string", paramType = "query",example="mp",required = true),
            @ApiImplicitParam(name="code",value="微信回调的code",dataType="string", paramType = "query",example="061SBT1u0oXqVh1HYo2u0KgD1u0SBT1G",required = true),
    })
    @AuthIgnore
    @ResponseBody
    @PostMapping("company/validCode/{diy}")
    public Msg getUserInfoByFace(@PathVariable String diy , String code, HttpServletRequest request,HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security==null){
            return Msg.fail("链接访问无效，不存在接入的公众号");
        }
        JSONObject tObject = WXUtil.getUserTokenByCode(code,security.getAppId());
        String openId = tObject.getString("openid");
        String user_token = tObject.getString("access_token");
        //判断数据库是否有记录信息
        CompanyUserVO companyUserVO = CompanyUserService.getCompanyVOUserByOpenid(openId);
        if(null == companyUserVO){//查询wx接口，获取数据，存储到数据库
            TbCompanyUser tbCompanyUser = new TbCompanyUser();
            WxUserInfoPO wxUserInfoPO = JSONObject.toJavaObject(WXUtil.getUserByUserToken(user_token, openId), WxUserInfoPO.class);
            wxUserInfoPO.setAppId(security.getAppId());//记录公众号
            tbCompanyUser.setOpenid(wxUserInfoPO.getOpenid());
            tbCompanyUser.setName(wxUserInfoPO.getNickname());
            tbCompanyUser.setAuth(0);//未认证
            tbCompanyUser.setId(UUID.randomUUID().toString());
            CompanyUserService.insertOne(wxUserInfoPO);
            CompanyUserService.insertOne(tbCompanyUser);
            companyUserVO = new CompanyUserVO();
            BeanUtils.copyProperties(wxUserInfoPO, companyUserVO);
            tbCompanyUser.setName(wxUserInfoPO.getNickname());
            tbCompanyUser.setAuth(0);//未认证
            companyUserVO.setId(tbCompanyUser.getId());
        }
        String webToken = MD5Util.encrypt(companyUserVO.getId()+UUID.randomUUID().toString());
        RedisUtils.set(Constants.COMPANY_USER_TOKEN+"::"+webToken,companyUserVO,7200,TimeUnit.SECONDS);
        request.getSession().setAttribute(Constants.COMPANY_USER_TOKEN,webToken);
        response.setHeader(Constants.COMPANY_USER_TOKEN,webToken);
        return Msg.success().add("companyUser",companyUserVO).add("companyUserToken",webToken);
    }

    @ApiOperation(value = "隐式获取用户信息",notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="diy",value="公众号唯一标识",dataType="string", paramType = "query",example="mp",required = true),
            @ApiImplicitParam(name="code",value="微信回调的code",dataType="string", paramType = "query",example="061SBT1u0oXqVh1HYo2u0KgD1u0SBT1G",required = true),
    })
    @AuthIgnore
    @ResponseBody
    @PostMapping("company/validCodeSilently/{diy}")
    public Msg getUserInfoBySilently(@PathVariable String diy , String code, HttpServletRequest request,HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security==null){
            return Msg.fail("链接访问无效，不存在接入的公众号");
        }
        JSONObject tObject = WXUtil.getUserTokenByCode(code,security.getAppId());
        String openId = tObject.getString("openid");
        String user_token = tObject.getString("access_token");
        //判断数据库是否有记录信息
        CompanyUserVO companyUserVO = CompanyUserService.getCompanyVOUserByOpenid(openId);
        if(null == companyUserVO){
            return Msg.fail("用户尚未授权").setCode(101).add("isAccess",false) .add("appId",security.getAppId()).add("state","validCode/"+diy);
        }
        String webToken = MD5Util.encrypt(companyUserVO.getId()+UUID.randomUUID().toString());
        request.getSession().setAttribute(Constants.COMPANY_USER_TOKEN,webToken);
        request.getSession().setAttribute(Constants.COMPANY_USER_TOKEN+"::"+webToken,companyUserVO);
        response.setHeader(Constants.COMPANY_USER_TOKEN,webToken);
        return Msg.success().add("companyUser",companyUserVO).add(Constants.COMPANY_USER_TOKEN,webToken)
                .add("isAccess",true);
    }



    @AuthIgnore
    @ApiOperation(value = "获取JsSDKWxConfig",notes = "获取JsSDKWxConfig")
    @ApiImplicitParams({
            @ApiImplicitParam(name="diy",value="公众号唯一标识",dataType="string", paramType = "query",example="mp",required = true)})
    @RequestMapping("getJsWxConfig/{diy}")
   public Msg getJsWxConfig(@PathVariable String diy,String url) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security == null){
            return Msg.fail("公众号diy信息获取失败，可能该公众号配置尚未接入");
        }
        JsapiTicket jt=JsapiUtil.getJsapiTicket(security.getAppId());
        String ticket=jt.getTicket();
        Map<String, String> t= Sign.sign(ticket,security.getAppId(), url.toString());
        return Msg.success().add("sign",t);
   }























    /**
     * 微信客户端交互 post
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @ApiIgnore()
    @PostMapping("access/{diy}")
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



    @ApiIgnore()
    @RequestMapping("sendTemplateMessage")
    public Object sendTemplateMessage(String token,String openid) throws IOException {
       return  WXUtil.sendTemplateMessage(WXUtil.TOKEN,null);
    }
    @ApiIgnore()
    @RequestMapping("getUserInfo")
    public String getUserInfo(String token,String openid) throws Exception {
       return   WXUtil.getUserByUserToken(token,openid).toJSONString();
    }

    @ApiIgnore()
    @AuthIgnore
    @RequestMapping("createMenu/{diy}")
    public Msg createMenu(@PathVariable String diy,HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security == null){
            return Msg.fail("处理的公众号尚未接入");
        }
        String menu = JSONObject.toJSONString(WXUtil.initMenu(security.getAppId(), security.getDiyDomain()));
       JSONObject result = WXUtil.createMenu(security.getAppId(),menu);
       if(result!=null){
           return Msg.success(result.getString("errmsg")).setCode(result.getInteger("errcode"));
       }
       return Msg.fail();
    }
}
