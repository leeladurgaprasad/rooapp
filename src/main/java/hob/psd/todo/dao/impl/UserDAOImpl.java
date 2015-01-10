package hob.psd.todo.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hob.psd.todo.dao.UserDAO;
import hob.psd.todo.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import static hob.psd.todo.constants.CommonConstants.*;

@Repository("userDaoImpl")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public boolean saveUser(UserDTO userDTO) {

		if (null == userDTO) {
			return FALSE;
		}

		try {
			sessionFactory.getCurrentSession().save(userDTO);
		} catch (Exception ex) {
			return FALSE;
		}

		return TRUE;
	}

	@Override
	public UserDTO getUserByUserName(String userName) {

		UserDTO userDTO = null;

		try {

			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class);
			criteria.add(Restrictions.eq("userName", userName));
			userDTO = (UserDTO) criteria.uniqueResult();
			return userDTO;

		} catch (Exception ex) {
			logger.error("unable to get user by username ",ex);
		}
		return userDTO;
	}

    @Override
    public UserDTO getUserByUserId(int userId) {
        UserDTO userDTO = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(UserDTO.class);
            criteria.add(Restrictions.eq("userId",userId));
            userDTO = (UserDTO) criteria.list().get(0);
            return userDTO;
        } catch (Exception ex) {
            logger.error("unable to get user by userId",ex);
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserDTO.class);
            criteria.add(Restrictions.eq("email",email));
            userDTO = (UserDTO) criteria.uniqueResult();
            return userDTO;
        } catch (Exception ex) {
            logger.error("unable to get user by email ",ex);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsersList() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            users = session.createQuery("from UserDTO as user").list();
        } catch (Exception ex) {
            logger.error("unable to retrives users "+ex);
        }
        return users;
    }
}
