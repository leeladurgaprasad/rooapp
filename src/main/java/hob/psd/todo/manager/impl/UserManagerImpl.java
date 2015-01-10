package hob.psd.todo.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hob.psd.todo.dao.UserDAO;
import hob.psd.todo.dto.UserDTO;
import hob.psd.todo.bean.User;
import hob.psd.todo.manager.UserManager;
import hob.psd.todo.mapper.config.UserDTOMapper;

import java.util.ArrayList;
import java.util.List;

@Service("userManager")  
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserDTOMapper userDTOMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean saveUser(User user) {
		UserDTO userDTO = userDTOMapper.map(user, UserDTO.class);
		return userDao.saveUser(userDTO);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		UserDTO userDTO = userDao.getUserByUserName(userName);
		User user = userDTOMapper.map(userDTO,User.class);
		return user;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUserByUserId(int userId) {
        UserDTO userDTO = userDao.getUserByUserId(userId);
        User user = userDTOMapper.map(userDTO,User.class);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        UserDTO userDTO = userDao.getUserByEmail(email);
        User user = userDTOMapper.map(userDTO,User.class);
        return  user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserDTO> userDTOs = userDao.getUsersList();
        List<User> users = new ArrayList<User>();
        for(UserDTO userDTO: userDTOs) {
            users.add(userDTOMapper.map(userDTO,User.class));
        }
        return users;
    }
}
