package com.miao.config;

import com.miao.config.RedisCache.ShiroRedisCacheManager;
import com.miao.core.shiro.UserRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactory(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         * 添加Shiro内置过滤器
         *      常用的过滤器:
         *          anon: 无序认证(登录)也可以访问
         *          authc: 必须认证才可以访问
         *          user: 如果使用rememberMe的功能可以直接访问
         *          perms: 该资源必须授权才可以访问
         *          role: 该资源必须得到角色授权才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        //anon必须在filterMap.put("/*", "authc");之前否则无效

        //放行/test
        filterMap.put("/user/test", "anon");

        //放行/index
        filterMap.put("/user/index", "anon");

        //放行/login_page
        filterMap.put("/user/login_page", "anon");

        //放行/register_page
        filterMap.put("/user/register_page", "anon");

        //放行/login
        filterMap.put("/user/login", "anon");

        //放行/register
        filterMap.put("/user/register", "anon");

        //授权过滤器
        filterMap.put("/user/vip", "perms[sysUser:vip]");

        //使用通配符* 例子:("/user/*")
        filterMap.put("/user/*", "authc");

        //修改跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/user/login_page");

        //设置为授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSessionManager
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间-单位:毫秒
        sessionManager.setGlobalSessionTimeout(1000*60*30);
        return sessionManager;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //Redis实现Session缓存；jedis
    //CacheManager导入Shiro
    //未启用:目的解决session共享
    @Bean
    public CacheManager RedisCacheManager(){
        return new ShiroRedisCacheManager();
    }

}
