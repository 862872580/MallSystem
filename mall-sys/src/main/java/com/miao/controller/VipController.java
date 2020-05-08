package com.miao.controller;

import com.miao.service.RedisService;
import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VipController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisService redisService;
    /**
     * @return 跳转到会员页面
     */
    @RequestMapping("/user/vip")
    public String vip(){
        return "vip";
    }

    /**
     * @return 跳转到会员开通页面
     */
    @RequestMapping("/user/open_vip_page")
    public String open_vip_page(){
        return "openvip";
    }

    /**
     * 开通会员
     * @param
     * @return
     */
    @RequestMapping("/user/openvip")
    public String openvip(){
        sysUserService.openVip();
        redisService.openVip();
        return "vip";
    }

}
