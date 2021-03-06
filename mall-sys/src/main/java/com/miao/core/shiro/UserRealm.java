package com.miao.core.shiro;

import com.miao.entity.SysUserEntity;
import com.miao.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * 自定义Realm
 */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService service;
    @Autowired
    private SessionDAO sessionDAO;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行授权逻辑");

        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("sysUser:vip");
        Subject subject = SecurityUtils.getSubject();
        SysUserEntity sysUserEntity = (SysUserEntity) subject.getPrincipal();
        info.addStringPermission(sysUserEntity.getPerms());

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("执行认证逻辑");

        //这里编写shiro判断逻辑, 判断用户名和密码
        //1.判断用户名是否存在
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        SysUserEntity sysUserEntity = service.findByName(token.getUsername());

        if(sysUserEntity == null || sysUserEntity.getDel_flag() == 0){
            //用户名不存在或已注销
            return null;
        }

        /**
         * 判断是否已经登录
         *      已登录将原session剔除,在放入现session
         */
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

        //判断密码是否正确
        if( password.equals( sysUserEntity.getPassword() ) ){
            // 获取所有session
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            for (Session session: sessions) {
                SysUserEntity sysUser = (SysUserEntity)session.getAttribute("USER_SESSION");
                // 如果session里面有当前登陆的，则证明是重复登陆的，则将其剔除
                if( sysUser!=null ){
                    if( username.equals( sysUser.getUsername() ) ){
                        session.setTimeout(0);
                    }
                }
            }
        }
        //2.判断密码
        /**
         * user传递给执行认证逻辑方法
         * user.getPassword()判断密码是否正确
         */

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", sysUserEntity);

        return new SimpleAuthenticationInfo(sysUserEntity, sysUserEntity.getPassword(), "");
    }
}
