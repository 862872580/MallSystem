package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.apache.shiro.SecurityUtils.getSubject;

@Component
public class RedisServiceImpl implements com.miao.service.RedisService {

    @Resource(name = "redisTemplate1")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisTemplate1")
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void setString(String key, Object value){
        set(key,value,null);
    }

    @Override
    public void setString(String key, Object value, Long timeout){
        set(key,value,timeout);
    }

    @Override
    public void set(String key, Object value, Long timeout){
        if (value == null){
            return;
        }
        if(value instanceof String){
            stringRedisTemplate.opsForValue().set(key, (String) value);
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        }
    }

    @Override
    public Object getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取redis中以某字符串为前缀的KEY列表代码
     */
    public Set<String> selectAll(String s) {
        Set<String> keys = redisTemplate.keys(s);
        return keys;
    }

    /**
     * 批量删除
     * @param set 模糊查询出的key的集合
     */
    private void deleteSet(Set<String> set) {
        redisTemplate.delete(set);
    }

    /**
     * 开通会员:设置过期
     */
    @Override
    public void openVip() {
        SysUserEntity sysUserEntity = (SysUserEntity) getSubject().getPrincipal();
        System.out.println(sysUserEntity);
        String username = "Vip:" + sysUserEntity.getUsername();
        String time = String.valueOf(new Date().getTime());
        setString(username,time);
    }

    @Override
    public void clearVip() {
        //获取以 s 为前缀的数据集合
        String s = "Vip:*";
        Set<String> set = selectAll(s);
        Set<String> usernames = new HashSet();
        //遍历Set
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
            String openTime = (String) getString(str);
            long l = Long.parseLong(openTime);
            long month = 30*24*60*60*1000;
            if (l < new Date().getTime()-month){
                System.out.println(str);
                usernames.add(str);
            }
        }
        //删除redis中数据
        deleteSet(usernames);

        //修改mysql中会员状态
        it = usernames.iterator();
        while (it.hasNext()) {
            String str = it.next();
            String username = str.replaceFirst("Vip:", "");
            SysUserEntity sysUserEntity = sysUserMapper.selectByName(username);
            String perms = "common";
            sysUserEntity.setPerms(perms);
            sysUserMapper.updateUser(sysUserEntity);
        }
    }

}
