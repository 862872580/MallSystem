package com.miao.service;

public interface RedisService {

    void setString(String key, Object value);

    void setString(String key, Object value, Long timeout);

    void set(String key, Object value, Long timeout);

    Object getString(String key);

    void openVip();

    void clearVip();
}
