package com.prasad.roostack.dao;

import com.prasad.roostack.dto.UserDTO;

import java.util.List;


public interface UserDAO {
	public boolean saveUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
	public UserDTO getUserByUserName(String userName);
    public UserDTO getUserByEmail(String email);
    public UserDTO getUserByUserId(int userId);
    public UserDTO getUserByAccessKey(String authKey);
    public List<UserDTO> getUsersList();
}
