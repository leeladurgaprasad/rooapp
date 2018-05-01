package com.prasad.roostack.manager;

import com.prasad.roostack.bean.User;

import java.util.List;


public interface UserManager {
		

	public boolean saveUser(User user); // TODO : need to change return type
    public User updateUser(User user);
	public User getUserByUserName(String userName);
    public User getUserByEmail(String email);
    public User getUserByUserId(int userId);
    public User getUserByAccessKey(String authKey);
    public List<User> getAllUsers();
	
}
