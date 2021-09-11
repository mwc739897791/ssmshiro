package com.zking.ssm.shiro;

import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> role = iUserService.getRole(username);
        Set<String> permission = iUserService.getPermission(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取参数中提交过来的账号密码
        String username = authenticationToken.getPrincipal().toString();
        String password = authenticationToken.getCredentials().toString();
        //通过用户账号，找到该用户在数据库中的所有数据
        User u=new User();
        u.setUsername(username);
        u.setPassword(password);
        User user = iUserService.doLogin(u);
        if(null==user){
            throw  new RuntimeException("账号不存在");}
        //将找到的数据封装到身份验证信息中
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo
                (user.getUsername(),user.getPassword(),ByteSource.Util.bytes(user.getSalt()),this.getName());
        return authenticationInfo;
    }
}
