package com.util;
import com.Form.FormServiceImpl;
import com.Redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.*;

@Component
public class timingService {
    @Autowired
    FormServiceImpl formService;

//    //5秒执行一次
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() throws Exception {
//    }

//    //每30分钟刷新鉴权
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void RefreshToken() throws Exception {
//    }


    /***
     * @function 使用定时器设置表单的起始时间
     * @tips 这个是每日提交的第一个表单
     * @return 无
     * @throws Exception
     */
    //使用cron表达式设置定时器
    //每天6.00-11.00
    @Scheduled(cron = "0 0 6 * * ? ")
    public void setEveryDayFirstForm() throws Exception {
        addForm(5,1);
    }

    @Scheduled(cron = "0 0 11 * * ? ")
    //每天11.00-21.00
    public void setEveryDaySecondForm() throws Exception {
        addForm(10,2);
    }

    public void addForm(int hour,int formNum){
        //获取到时间戳（秒
        long startTime = System.currentTimeMillis()/1000;
        //进行一个时间戳的取整
        startTime/=3600;
        startTime*=3600;
        long endTime = startTime+3600*hour;
        String formCode = "Fom"+startTime+PubicMethod.getAcademeCode();
        Date formDate = new Date(System.currentTimeMillis());
        Map<String,Object> cMap = new HashMap<>();
        cMap.put("endTime",endTime);
        cMap.put("startTime",startTime);
        cMap.put("formCode",formCode);
        cMap.put("formDate",formDate);
        cMap.put("formNum",formNum);
        formService.addFormStyle(cMap);
    }

}
