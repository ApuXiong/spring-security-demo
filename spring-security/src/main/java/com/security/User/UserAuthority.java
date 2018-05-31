package com.security.User;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
	public UserAuthority(String authority) {
		this.authority = authority;
	}

	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
