package com.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("failureHandler")
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
		logger.info("登录失败", exception);

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(exception.getMessage());

//		重定向
//		new DefaultRedirectStrategy().sendRedirect(request, response, "/404.html");
	}

}
