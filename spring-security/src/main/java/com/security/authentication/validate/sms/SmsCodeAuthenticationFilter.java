package com.security.authentication.validate.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private String MOBILE_PARAMETER = "mobile";
	private String CODE_PARAMETER = "code";
	private boolean postOnly = true;

	// 设置请求
	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/user/mobile", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);
		String code = obtainCode(request);

		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile, code);

		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}


	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(MOBILE_PARAMETER);
	}

	protected String obtainCode(HttpServletRequest request) {
		return request.getParameter(CODE_PARAMETER);
	}

	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setMobileParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.MOBILE_PARAMETER = usernameParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return MOBILE_PARAMETER;
	}

}
