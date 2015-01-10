package hob.psd.todo.manager;

import hob.psd.todo.bean.User;

import java.util.List;


public interface UserManager {
		

	public boolean saveUser(User user); // TODO : need to change return type
	public User getUserByUserName(String userName);
    public User getUserByEmail(String email);
    public User getUserByUserId(int userId);
    public List<User> getAllUsers();
	
}
