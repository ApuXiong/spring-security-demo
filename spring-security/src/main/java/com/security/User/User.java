package com.security.User;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements UserDetails {
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setAuthorities("1","2");
        this.birthday = new Date();
    }

    private String username;

	private String password;

	private List<UserAuthority> authorities;

	private Date birthday;

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setAuthorities(String ...authorities) {
		this.authorities = new ArrayList<>();
		for (String authority : authorities) {
			this.authorities.add(new UserAuthority(authority));
		}
	}
}
