package com.DeviceData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeviceService {
    @Autowired
    DeviceMapper deviceMapper;
    public int addDeviceByUserCode(Map<String,Object> param)
    {
        return deviceMapper.addDeviceByUserCode(param);
    }
    public List<Map<String,Object>> getDeviceByUserCode(Map<String,Object> param, int page, int limit){
        return  deviceMapper.getDeviceByUserCode(param);
    }
    public String getDeviceByDeviceIMEI(String DeviceIMEI){
        return deviceMapper.getDeviceByDeviceIMEI(DeviceIMEI);
    }
    public String getDeviceByDeviceCode(String DeviceCode){
        return deviceMapper.getDeviceByDeviceCode(DeviceCode);
    }

    public int deleteDeviceByDianXinCode(String DeviceCode){
        return deviceMapper.deleteDeviceByDianXinCode(DeviceCode);
    }
    public int updataDeviceByDeviceIMEI(Map<String,String> param){
        return deviceMapper.updataDeviceByDeviceIMEI(param);
    }

}
