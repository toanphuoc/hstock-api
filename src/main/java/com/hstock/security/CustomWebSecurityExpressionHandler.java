package com.hstock.security;

import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

public class CustomWebSecurityExpressionHandler extends AbstractSecurityExpressionHandler<FilterInvocation>{

	@Override
	protected SecurityExpressionOperations createSecurityExpressionRoot(
			Authentication authentication, FilterInvocation fileInvocation) {
		CustomWebSecurityExpressionRoot customWebSecurityExpressionRoot = new CustomWebSecurityExpressionRoot(authentication, fileInvocation);
		customWebSecurityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
		customWebSecurityExpressionRoot.setTrustResolver(new AuthenticationTrustResolverImpl());
		return customWebSecurityExpressionRoot;
	}

}
