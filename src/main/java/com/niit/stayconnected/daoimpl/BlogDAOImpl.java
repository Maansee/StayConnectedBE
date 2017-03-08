package com.niit.stayconnected.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.stayconnected.dao.BlogDAO;
import com.niit.stayconnected.model.Blog;

@Repository
@Transactional
public class BlogDAOImpl implements BlogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Blog> list() {
		String hql = "from Blog";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();

		return list;
	}

	@Transactional
	public Blog getBlog(int BlogId) {
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, BlogId);
		
	}

	@Transactional
	public List<Blog> getAllBlogs() {
		String hql = "from Blog where status = 'A'";
		Query query=  getSession().createQuery(hql);
		return query.list();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public List<Blog> getAllBlogs(String userID) {
		String hql = "from Blog where userID = '"+userID+"'";
		Query query= getSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);
		return true;
	}

	@Transactional
	public boolean update(Blog blog) {
		sessionFactory.getCurrentSession().update(blog);
		return true;
	}

	@Transactional
	public void delete(int blogId) {
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blog);	
		
	}

	@Transactional
	public Blog get(int blogId) {
		String hql = "from Blog where id=" + blogId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		List<Blog> list = (List<Blog>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
