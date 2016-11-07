package com.dexingworld.hanfu.session;

import com.dexingworld.hanfu.common.GlobalConsts;
import com.dexingworld.hanfu.middleware.redis.RedisCacheManager;
import com.dexingworld.hanfu.repository.entity.User;
import com.dexingworld.hanfu.utils.CookieUtils;
import com.dexingworld.hanfu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Component
public class SessionProcess {

    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 保存登陆cookie
     * @param user
     * @param response
     * @param timeout
     * @return
     */
    public String login(User user,HttpServletResponse response,Long timeout){
        if(user != null){
            String token = StringUtils.uuidString();
            //存入redis
            redisCacheManager.put(token,user,timeout);
            String accessToken = GlobalConsts.COOKIE_PREFIX+token;
            int expiry = Long.valueOf(timeout.longValue() / 1000L).intValue();
            CookieUtils.setCookieHttpOnly(response, GlobalConsts.SESSION_COOKIE_KEY, accessToken, (String) null, "/", Integer.valueOf(expiry));
            return token;
        }else {
            return "";
        }
    }

    /**
     * 从cokkie中获取accessToke
     * @param request
     * @return
     */
    public String getAcessToken(HttpServletRequest request){
        String sessionInfo = CookieUtils.getCookie(request,GlobalConsts.SESSION_COOKIE_KEY);
        if(StringUtils.isNotEmpty(sessionInfo)){
            return sessionInfo.substring(GlobalConsts.COOKIE_PREFIX.length());
        }else {
            sessionInfo = request.getHeader(GlobalConsts.SESSION_COOKIE_KEY);
            return StringUtils.isNotEmpty(sessionInfo) ? sessionInfo.substring(GlobalConsts.COOKIE_PREFIX.length()):request.getParameter("accessToken");
        }
    }

    public User getSessionUser(String accessToken){
        if(StringUtils.isEmpty(accessToken)){
            return null;
        }
        return (User)redisCacheManager.get(accessToken);
    }

    /**
     * 注销
     * @param request
     * @return
     */
    public void logout(HttpServletRequest request){
        String accessToken = getAcessToken(request);
        if(StringUtils.isEmpty(accessToken)){
            return;
        }
        redisCacheManager.delete(accessToken);
    }
}
