package com.niit.stayconnected.daoimpl;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.stayconnected.dao.UserDAO;
import com.niit.stayconnected.model.UserInfo;


@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/*public UserInfo authenticate(UserInfo user) {
		System.out.println("h9");
		
			Session session=sessionFactory.openSession();
				System.out.println("h10");
				System.out.println("name"+user.getUsername()+"password"+user.getPassword());
		
				List<UserInfo> validUser = session.createQuery("from UserInfo where username='"+user.getUsername()+"'  and password='"+user.getPassword()+"'").list();
					if(validUser!=null && !validUser.isEmpty()){
						System.out.println("Hellow123");
						return validUser.get(0);
				}
					return null;
	}*/

	public UserInfo authenticate(UserInfo user) {
		System.out.println("h9");

		Session session=sessionFactory.openSession();
		System.out.println("h10");

		Query query=session.createQuery("from UserInfo where username='"+user.getUsername()+"'  and password='"+user.getPassword()+"'");
		//select * from User where username='smith' and password='123'
		System.out.println("h11");

//		query.setString(0, user.getUsername());
//		query.setString(1, user.getPassword());
	    System.out.println("name"+user.getUsername());

	    UserInfo validUser=(UserInfo)query.uniqueResult();

		session.close();

		return validUser;
		
	}
	
	
	public void updateUser(UserInfo user) {
		Session session=sessionFactory.openSession();
		UserInfo existingUser=(UserInfo)session.get(UserInfo.class, user.getId());
		//update online status as true
		existingUser.setOnline(user.isOnline()); 
		
		session.update(existingUser);
		session.flush();
		session.close();		
	}

	
	public UserInfo registerUser(UserInfo user) {
		Session session=sessionFactory.openSession();
		session.save(user);
		session.flush();
		session.close();
		
		return user;
	}

	public List<UserInfo> getAllUsers(UserInfo user) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery(
		//"select * from proj2_user where username in (select username from UserInfo where username!=? minus(select to_id from proj2_friend where from_id=? union select from_id from proj2_friend where to_id=?))");
		"select * from UserInfo where username in (select username from UserInfo where username!=? minus(select to_id from proj2_friend where from_id=? and status!='D' union select from_id from proj2_friend where to_id=? and status!='D'))");

		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(UserInfo.class);
		@SuppressWarnings("unchecked")
		List<UserInfo> users=query.list();
		System.out.println(users);
		session.close();
		return users;
	}

	




}