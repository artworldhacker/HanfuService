package com.dexingworld.hanfu.web.controller.weixin;

import com.dexingworld.hanfu.biz.weixin.WeixinService;
import com.dexingworld.hanfu.common.parameter.weixin.WeixinConfig;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.common.response.weixin.TokenResponse;
import com.dexingworld.hanfu.utils.PropertieUtils;
import com.dexingworld.hanfu.weixin.MsgType;
import com.dexingworld.hanfu.weixin.model.ImageMessage;
import com.dexingworld.hanfu.weixin.model.InputMessage;
import com.dexingworld.hanfu.weixin.model.OutputMessage;
import com.dexingworld.hanfu.weixin.util.GetWeixinConfigUtils;
import com.dexingworld.hanfu.weixin.util.SHA1;
import com.dexingworld.hanfu.weixin.util.SerializeXmlUtil;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangpeng on 2016/10/13.
 */
@RestController
public class WeixinController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinController.class);
    private static String TOKEN = null;

    @Autowired
    private WeixinService weixinService;

    @PostConstruct
    public void init(){
        if(StringUtils.isEmpty(TOKEN)){
            TOKEN = PropertieUtils.getString("hanfu_token");
        }
    }

    @RequestMapping("/weixin/getToken")
    public ResultResponse accessToken(WeixinConfig weixinConfig){
        ResultResponse result = new ResultResponse();
        weixinConfig = GetWeixinConfigUtils.getWeixinConfig(weixinConfig);
        TokenResponse tokenResponse =  weixinService.accessToken(weixinConfig);
        if(tokenResponse == null){
            if(tokenResponse == null ){
                return result.makeFailure("请求微信获取token失败!");
            }
        }
        result.setResult(tokenResponse);
        if(tokenResponse.getErrcode() != null){
            return result.makeFailure("请求微信获取token失败!错误代码{"+tokenResponse.getErrcode()+"}，错误消息{"+tokenResponse.getErrmsg()+"}");
        }
        return result.makeSuccessful();
    }

    /**
     * 接收微信发送的消息
     * @param request
     * @param response
     */
    @RequestMapping("/weixin/receiveMsg")
    public ResultResponse weixin( HttpServletRequest request, HttpServletResponse response){
        ResultResponse resultResponse = new ResultResponse();
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            LOGGER.info(signature);
            LOGGER.info(timestamp);
            LOGGER.info(nonce);
            LOGGER.info(echostr);
            access(request, response);
        } else {
            // 进入POST聊天处理  
            LOGGER.info("enter post");
            try {
                // 接收消息并返回消息  
                acceptMessage(request, response);
            } catch (IOException e) {
                return resultResponse.makeFailure(e.getMessage());
            }
        }
        return resultResponse.makeSuccessful();
    }


    /**
     * 验证URL真实性
     */
    private String access(HttpServletRequest request, HttpServletResponse response) {
        // 验证URL真实性
        LOGGER.info("进入验证access");
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        List<String> params = Lists.newArrayList();
        params.add(TOKEN);
        params.add(timestamp);
        params.add(nonce);
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            try {
                response.getWriter().write(echostr);
                LOGGER.info("成功返回 echostr：" + echostr);
                return echostr;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("失败 认证");
        return null;
    }


    /**
     * 接收消息
     * @param request
     * @param response
     * @throws java.io.IOException
     */
    private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
    /*    StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }*/
        byte[] byteData = IOUtils.toByteArray(in);
        String xmlMsg = new String(byteData,"UTF-8");
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg);

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if (msgType.equalsIgnoreCase(MsgType.Text.toString())) {
            // 文本消息
            LOGGER.info("开发者微信号：" + inputMsg.getToUserName());
            LOGGER.info("发送方帐号：" + inputMsg.getFromUserName());
            LOGGER.info("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
            LOGGER.info("消息内容：" + inputMsg.getContent());
            LOGGER.info("消息Id：" + inputMsg.getMsgId());

            StringBuffer str = new StringBuffer();
            str.append("<xml>");
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
            str.append("<CreateTime>" + returnTime + "</CreateTime>");
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
            str.append("<Content><![CDATA[你说的是：" + inputMsg.getContent() + "，吗？]]></Content>");
            str.append("</xml>");
            LOGGER.info(str.toString());
            response.getWriter().write(str.toString());
        }
        // 获取并返回多图片消息
        if (msgType.equalsIgnoreCase(MsgType.Image.toString())) {
            LOGGER.info("获取多媒体信息");
            LOGGER.info("多媒体文件id：" + inputMsg.getMediaId());
            LOGGER.info("图片链接：" + inputMsg.getPicUrl());
            LOGGER.info("消息id，64位整型：" + inputMsg.getMsgId());

            OutputMessage outputMsg = new OutputMessage();
            outputMsg.setFromUserName(servername);
            outputMsg.setToUserName(custermname);
            outputMsg.setCreateTime(returnTime);
            outputMsg.setMsgType(msgType);
            ImageMessage images = new ImageMessage();
            images.setMediaId(inputMsg.getMediaId());
            outputMsg.setImage(images);
            LOGGER.info("xml转换：/n" + xs.toXML(outputMsg));
            response.getWriter().write(xs.toXML(outputMsg));

        }
    }


}
