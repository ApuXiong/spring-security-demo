package com.security.authentication.validate.code;

import com.security.authentication.validate.ValidateCode;
import com.security.authentication.validate.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeAuthenticationFilter extends OncePerRequestFilter {
	private String USER_PARAMETER = "username";
	private String CODE_PARAMETER = "code";
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Autowired
	private ValidateCodeService validateCodeService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (!pathMatcher.match("/login/form", request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}

		if (validateCodeService.validateCode(new ValidateCode(obtainUsername(request), obtainCode(request)))) {
			logger.info("验证码校验通过");
			filterChain.doFilter(request, response);
		} else {
			throw new InternalAuthenticationServiceException("验证码错误");
		}
	}

	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(USER_PARAMETER);
	}

	protected String obtainCode(HttpServletRequest request) {
		return request.getParameter(CODE_PARAMETER);
	}
}
