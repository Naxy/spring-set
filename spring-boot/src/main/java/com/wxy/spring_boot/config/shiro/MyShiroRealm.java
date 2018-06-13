package com.wxy.spring_boot.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxy.spring_boot.user.domain.SysPermission;
import com.wxy.spring_boot.user.domain.SysRole;
import com.wxy.spring_boot.user.domain.UserInfo;
import com.wxy.spring_boot.user.service.UserInfoService;

public class MyShiroRealm extends AuthorizingRealm {
	static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	@Autowired
	UserInfoService userInfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("principals:" + principals);
		SimpleAuthorizationInfo  authorizationInfo = new SimpleAuthorizationInfo ();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		for(SysRole role : userInfo.getRoleList()){
			authorizationInfo.addRole(role.getRole());
			for(SysPermission p : role.getPermissions()){
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		logger.info("authentication username:" + username);
		UserInfo userInfo = userInfoService.findByUsername(username);
		if (userInfo == null)
			return null;
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(),
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()), userInfo.getName());
		return authenticationInfo;
	}

}
