package com.dexingworld.hanfu.web.session;

import com.dexingworld.hanfu.common.GlobalConsts;
import com.dexingworld.hanfu.middleware.redis.RedisCacheManager;
import com.dexingworld.hanfu.repository.entity.User;
import com.dexingworld.hanfu.utils.CookieUtils;
import com.dexingworld.hanfu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            CookieUtils.setCookieHttpOnly(response, "Authorization", accessToken, (String) null, "/", Integer.valueOf(expiry));
            return token;
        }else {
            return "";
        }
    }
}
