package com.prasad.roostack.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prasad.roostack.dao.UserDAO;
import com.prasad.roostack.dto.UserDTO;
import com.prasad.roostack.bean.User;
import com.prasad.roostack.manager.UserManager;
import com.prasad.roostack.mapper.config.UserDTOMapper;

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User updateUser(User user) {
        UserDTO userDTO = userDTOMapper.map(user,UserDTO.class);
        userDTO = userDao.updateUser(userDTO);
        user = userDTOMapper.map(userDTO,User.class);
        return user;
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUserByAccessKey(String authKey) {
        UserDTO userDTO = userDao.getUserByAccessKey(authKey);
        User user = userDTOMapper.map(userDTO, User.class);
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
