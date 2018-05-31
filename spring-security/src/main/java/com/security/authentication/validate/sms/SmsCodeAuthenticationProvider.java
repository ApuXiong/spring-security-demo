package com.security.authentication.validate.sms;

import com.security.User.User;
import com.security.User.UserService;
import com.security.authentication.validate.ValidateCode;
import com.security.authentication.validate.ValidateCodeService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private ValidateCodeService validateCodeService;

	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		ValidateCode validateCode = (ValidateCode) authentication.getPrincipal();
		if (!validateCodeService.validateCode(validateCode)) {
			throw new InternalAuthenticationServiceException("验证码校验不通过");
		}

		User user = userService.getUserByMobile(validateCode.getKey());

		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public ValidateCodeService getValidateCodeService() {
		return validateCodeService;
	}

	public void setValidateCodeService(ValidateCodeService validateCodeService) {
		this.validateCodeService = validateCodeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
