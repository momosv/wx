package com.example.wx.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.example.wx.entity.AccessToken;
import com.example.wx.entity.TemplateData;
import com.example.wx.entity.WeChatTemplate;
import com.example.wx.wxmenu.Button;
import com.example.wx.wxmenu.ClickButton;
import com.example.wx.wxmenu.Menu;
import com.example.wx.wxmenu.ViewButton;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 16:24
 **/

public class WXUtil {
    //从微信后台拿到APPID和APPSECRET 并封装为常量
    public static  String TOKEN = "16_J8uFcZ-AELQ_dK3neSrmpGlnNevO09w2jWZsslUIZIqRqjrwbxY4wWrqSSXbw9GJg_cEnHujJ7fk7M23zihkBewhX-A8FSZkIJqzbSprOVOyB90-AnRzxSlfUalzwUaFPObbSv3xRl8jr6m7ADPcAGADGS";

    private static final String APPID = "wx550aceeb3b9271a4";
    private static final String APPSECRET = "2ec7cc8fa3c7c229c9244cf39affb31c";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static final String USER_DETAIL_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    private static final String USER_DETAIL_URL_BY_USER_TOKEN = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    private static final String USER_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static final String SEND_TEMPLAYE_MESSAGE_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";



public static Object sendTemplateMessage(String accessToken, WeChatTemplate weChatTemplate) {

    String jsonString = JSONObject.toJSONString(weChatTemplate);

     weChatTemplate = new WeChatTemplate();
    weChatTemplate.setTemplate_id("vHfhjBgslHV9DfHWRvvukHME6R5ykiMHmSN5PNMPmLI");
    weChatTemplate.setTouser("orXeJ1NIsDaEMtH1cnwoOTmYewL0");//此处是用户的OpenId

    weChatTemplate.setUrl("");

    Map<String, TemplateData> m = new HashMap<String, TemplateData>();
    TemplateData first = new TemplateData();
    first.setColor("#66CCFF");
    first.setValue("起床集合");
    m.put("first", first);
    TemplateData keyword1 = new TemplateData();
    keyword1.setColor("#66CCFF");
    keyword1.setValue("没理由啊");
    m.put("keyword1", keyword1);
    TemplateData keyword2 = new TemplateData();
    keyword2.setColor("#66CCFF");
    keyword2.setValue("now!!!");
    m.put("keyword2", keyword2);
    TemplateData remark = new TemplateData();
    remark.setColor("#66CCFF");
    remark.setValue("此处应有掌声");
    m.put("remark", remark);
    weChatTemplate.setData(m);
    try {
        WXUtil.sendTemplateMessage2(TOKEN, weChatTemplate);
        weChatTemplate.setTouser("orXeJ1EVNi1d8_0kgebkkQB-bn_g");//此处是用户的OpenId);
      return  WXUtil.sendTemplateMessage2(TOKEN, weChatTemplate);
    } catch (Exception e) {
    }
    return "faild";
}
    public static JSONObject sendTemplateMessage2(String accessToken, WeChatTemplate weChatTemplate) throws IOException {
        String jsonString = JSONObject.toJSONString(weChatTemplate);
        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);//发送 post请求 发送json数据   
        //发送 post请求 发送json数据
         JSONObject json = doPostStr(requestUrl, jsonString);
            String  errmsg = null;
            Integer result = null;
        if(json != null){

            result = json.getInteger("errcode");
            errmsg = json.getString("errmsg");

        }
        System.out.println(result+errmsg);
        return json;

        }




    /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     *
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.parseObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }

    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     *
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url,String outStr) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();//获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.parseObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() throws ClientProtocolException, IOException{
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉


        JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
        if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
            token.setToken(jsonObject.getString("access_token"));//取出access_token
            token.setExpiresIn(jsonObject.getInteger("expires_in"));//取出access_token的有效期
        }
        return token;
    }

    public static String upload(String filePath,String accessToken,String type) throws IOException {
        File file = new File(filePath);
        if(!file.exists()||!file.isFile()){
            throw  new IOException("文件不存在");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        URL urlObj =new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        con.setRequestProperty("Connection","Keep-Alive");
        con.setRequestProperty("Charset","UTF-8");

        String BOUNDARY="----------"+System.currentTimeMillis();
        con.setRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-dada;name=\"file\";filename=\""+file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");

        OutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(head);

        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut=new byte[1024];
        while((bytes=in.read(bufferOut))!=-1){
            out.write(bufferOut,0,bytes);
        }
        in.close();
        byte[] foot = ("\r\n--"+BOUNDARY+"--\r\n").getBytes("utf-8");

        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result=null;
        try{
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine())!=null){
                buffer.append(line);
            }
            if (result == null){
                result = buffer.toString();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                reader.close();
            }
        }
        JSONObject jsonObject =JSONObject.parseObject(result);
        System.out.println(jsonObject);
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type+"media_id";
        }
        String mediaId = jsonObject.getString(typeName);
        return mediaId;

    }
    public static String upload1(String filePath,String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }


       // String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+accessToken+"&type="+type;
        URL urlObj = new URL(url);
//连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();


        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);


//设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");


//设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);


        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");


        byte[] head = sb.toString().getBytes("utf-8");


//获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
//输出表头
        out.write(head);


//文件正文部分
//把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();


//结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线


        out.write(foot);


        out.flush();
        out.close();


        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
//定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }


        JSONObject jsonObj = JSONObject.parseObject(result);
        System.out.println(jsonObj);
//如果是图片消息，直接返回media_id，如果是其他类型的消息，会返回一个消息类型+media_id
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObj.getString(typeName);
        return mediaId;

    }

    /**

     * 组装菜单

     * @return

     */

    public static Menu initMenu(){

        Menu menu = new Menu();

        ClickButton button11 = new ClickButton();

        button11.setName("了解杰瑞教育");

        button11.setType("click");

        button11.setKey("11");



        ClickButton button12 = new ClickButton();

        button12.setName("加入杰瑞教育");

        button12.setType("click");

        button12.setKey("12");



        ViewButton button21 = new ViewButton();

        button21.setName("杰瑞教育官网");

        button21.setType("view");

        button21.setUrl("http://www.jerehedu.com");



        ViewButton button22 = new ViewButton();

        button22.setName("杰瑞教育新闻网");

        button22.setType("view");

        button22.setUrl("http://www.jredu100.com");



        ViewButton button31 = new ViewButton();

        button31.setName("个人中心");

        button31.setType("view");

        button31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx550aceeb3b9271a4&redirect_uri=http://192.168.0.103/page/wx/valid&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");



        Button button1 = new Button();

        button1.setName("杰瑞教育"); //将11/12两个button作为二级菜单封装第一个一级菜单

       button1.setSub_button(new Button[]{button11,button12});



        Button button2 = new Button();

        button2.setName("相关网址"); //将21/22两个button作为二级菜单封装第二个二级菜单

       button2.setSub_button(new Button[]{button21,button22});



        menu.setButton(new Button[]{button31});// 将31Button直接作为一级菜单

        return menu;

    }

        public static int createMenu(String token,String menu) throws ClientProtocolException, IOException {

        int result = 0;
        String errmsg = "ok";
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, menu);
        if(jsonObject != null){

            result = jsonObject.getInteger("errcode");
            errmsg = jsonObject.getString("errmsg");

        }
        System.out.println(result+errmsg);
        return result;
       }
        public static JSONObject getUser(String token,String openId) throws ClientProtocolException, IOException {

        int result = 0;
        String errmsg = "ok";
        String url = USER_DETAIL_URL.replace("ACCESS_TOKEN", token).replace("OPENID",openId);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null && jsonObject.containsKey("errcode") &&   0!= jsonObject.getInteger("errcode")){
            result = jsonObject.getInteger("errcode");
            errmsg = jsonObject.getString("errmsg");
        }
        System.out.println(jsonObject);
        System.out.println(result+errmsg);
        return jsonObject;
       }

       public static JSONObject getUserByUserToken(String token,String openId) throws ClientProtocolException, IOException {

        int result = 0;
        String errmsg = "ok";
        String url = USER_DETAIL_URL_BY_USER_TOKEN.replace("ACCESS_TOKEN", token).replace("OPENID",openId);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null && jsonObject.containsKey("errcode") &&   0!= jsonObject.getInteger("errcode")){
            result = jsonObject.getInteger("errcode");
            errmsg = jsonObject.getString("errmsg");
        }
        System.out.println(jsonObject);
        System.out.println(result+errmsg);
        return jsonObject;
       }


    public static JSONObject getUserTokenByCode(String code) throws IOException {
        int result = 0;
        String errmsg = "ok";
        String url = USER_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET",APPSECRET).replace("CODE",code);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null && jsonObject.containsKey("errcode") &&   0!= jsonObject.getInteger("errcode")){
            result = jsonObject.getInteger("errcode");
            errmsg = jsonObject.getString("errmsg");
        }
        System.out.println(jsonObject);
        System.out.println(result+errmsg);
        return jsonObject;
    }

}













