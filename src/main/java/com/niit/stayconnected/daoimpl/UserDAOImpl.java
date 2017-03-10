package com.niit.stayconnected.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.stayconnected.dao.UserDAO;
import com.niit.stayconnected.model.UserInfo;

@SuppressWarnings("deprecation")
@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	@Transactional
	public UserInfo get(int userId, String password) {
		String hql = "from UserInfo where id=" + "'"+ userId+"'  and password = "
				+"'" + password + "'";
	   return getUserInfo(hql);
	}

	private UserInfo getUserInfo(String hql) {
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<UserInfo> list = (List<UserInfo>) query.list();
			
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
			return null;
	}

	@Transactional
	public UserInfo get(int userId) {
		return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, userId);
	}

	@Transactional
	public boolean save(UserInfo user) {
		sessionFactory.getCurrentSession().save(user);
		
		return true;
	}

	@Transactional
	public boolean update(UserInfo user) {
		sessionFactory.getCurrentSession().update(user);
		return true;
	}

	@Transactional
	public void delete(int userId) {
		UserInfo user = new UserInfo();
		user.setUserId(userId);
		sessionFactory.getCurrentSession().delete(user);
		
	}

	@Transactional
	public UserInfo authenticate(int userId, String password) {
		String hql = "from UserInfo where id= '" + userId + "' and " + " password ='" + password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) query.list();
		
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<UserInfo> getAllUsers(UserInfo user) {
		List<UserInfo> list = (List<UserInfo>) sessionFactory.getCurrentSession()
				.createCriteria(UserInfo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Transactional
	public void setOnline(String userID) {
		String hql =" UPDATE UserInfo	SET isOnline = 'Y' where id='" +  userID + "'" ;
	
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

	@Transactional
	public void setOffLine(String userID) {
		String hql =" UPDATE UserInfo	SET isOnline = 'N' where id='" +  userID + "'" ;
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}


		@Transactional
		public List<UserInfo> list() {
		/*	log.debug("->->Starting of the method list");*/
			@SuppressWarnings("unchecked")
			List<UserInfo> list = (List<UserInfo>) sessionFactory.getCurrentSession()
					.createCriteria(UserInfo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			return list;
		}


}