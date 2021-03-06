package com.niit.stayconnected.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.stayconnected.dao.FileUploadDAO;
import com.niit.stayconnected.model.ProfilePhoto;

@Transactional
@Repository
public class FileUploadDAOImpl implements FileUploadDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDAOImpl() {
	}

	public FileUploadDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void save(ProfilePhoto uploadFile) {
		Session session=sessionFactory.openSession();
		session.save(uploadFile);
		session.flush();
		session.close();
		
	}

	@Override
	public ProfilePhoto getFile(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ProfilePhoto where username=?");
		query.setParameter(0, username);
		ProfilePhoto uploadFile=(ProfilePhoto)query.uniqueResult();
		session.close();
		return uploadFile;
	}

}
 