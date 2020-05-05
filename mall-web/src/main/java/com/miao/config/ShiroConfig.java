package com.miao.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

        //filterMap.put("/welcome", "authc");

        //filterMap.put("/fail", "authc");

        //anon必须在filterMap.put("/*", "authc");之前否则无效
        filterMap.put("/test", "anon");

        //放行/login_success
        filterMap.put("/login_success", "anon");

        //授权过滤器
        filterMap.put("/welcome", "perms[user:welcome]");

        filterMap.put("/fail", "perms[user:fail]");

        //使用通配符* 例子:("/user/*")
        filterMap.put("/*", "authc");



        //修改跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        //设置为授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

}
