package com.util;

import com.IOT.service.appAccessSecurity.Authentication;
import com.IOT.utils.Constant;
import com.IOT.utils.HttpsUtil;
import com.IOT.utils.JsonUtil;
import com.IOT.utils.StreamClosedHttpResponse;
import com.Redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class timingService {
    @Autowired
    RedisService redisService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws Exception {
        String accessToken = Constant.accessToken;
        if(accessToken.equals("accessToken"))
        {
            //代表并没有鉴权成功,所以需要进行再次鉴权,
            new Authentication().FirstLogin();
            System.out.println("reportCurrentTime:reportCurrentTime"+Constant.accessToken);
        }
    }

    //每30分钟刷新鉴权
    @Scheduled(cron = "0 0/30 * * * ?")
    public void RefreshToken() throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        String refreshToken = getRefreshToken(httpsUtil);
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlRefreshToken = Constant.REFRESH_TOKEN;

        Map<String, Object> param_reg = new HashMap<>();
        param_reg.put("appId", appId);
        param_reg.put("secret", secret);
        param_reg.put("refreshToken", refreshToken);

        String jsonRequest = JsonUtil.jsonObj2Sting(param_reg);
        StreamClosedHttpResponse bodyRefreshToken = httpsUtil.doPostJsonGetStatusLine(urlRefreshToken, jsonRequest);
        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(bodyRefreshToken.getContent(), data.getClass());
        String accessToken = data.get("accessToken");

        Constant.accessToken = accessToken;
        System.out.println("RefreshToken:accessToken:" + accessToken);
    }

    @SuppressWarnings("unchecked")
    public static String getRefreshToken(HttpsUtil httpsUtil) throws Exception {

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;
        Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);
        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("refreshToken");
    }
}
