package com.security.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	public User getUserById(String username){
		User user = new User(username, "123456");
		return user;
	}

	public User getUserByMobile(String mobile){
		User user = new User(mobile, "123456");
		return user;
	}

	public List<User> getUsers(String queryName) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User(String.valueOf(i), "123456");
			users.add(user);
		}
		return users;
	}

	public int saveUser(User user) {
		return 1;
	}

	public int updateUser(User user){
		return 1;
	}
}
