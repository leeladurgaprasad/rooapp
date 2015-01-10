package hob.psd.todo.dao;

import hob.psd.todo.dto.UserDTO;

import java.util.List;


public interface UserDAO {
	public boolean saveUser(UserDTO userDTO);
	public UserDTO getUserByUserName(String userName);
    public UserDTO getUserByEmail(String email);
    public UserDTO getUserByUserId(int userId);
    public List<UserDTO> getUsersList();
}
