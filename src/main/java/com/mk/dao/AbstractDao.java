package com.mk.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mk.controller.CreateUpdateListController;
import com.mk.model.Identifiable;

@Repository("abstractDao")
public class AbstractDao<T extends Identifiable<U>, U extends Serializable>  {
	
	@Autowired
	protected SessionFactory		sessionFactory;
	
	private static final Logger	log	= LoggerFactory.getLogger(AbstractDao.class);

	@SuppressWarnings("unchecked")
	public T getById(Class<T> clazz,
			U id) {
	
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public Set<T> getByMultipleId(Class<T> clazz,
			Set<U> ids) {
	
		List<T> list = ((ids == null) || ids.isEmpty()) ? new ArrayList<T>() : sessionFactory
				.getCurrentSession()
				.createCriteria(clazz)
				.add(Restrictions.in("id", ids))
				.list();
		return new HashSet<T>(list);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByMultipleId(Class<T> clazz,
			List<U> ids) {
	
		return ((ids == null) || ids.isEmpty()) ? new ArrayList<T>() : sessionFactory
				.getCurrentSession()
				.createCriteria(clazz)
				.add(Restrictions.in("id", ids))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public U save(T object) {
	
		U result = (U) sessionFactory.getCurrentSession().save(object);
		sessionFactory.getCurrentSession().flush();/* immediate flush for in-single-session operation */
		return result;
	}
	
	public void saveOrUpdate(T object) {
	
		sessionFactory.getCurrentSession().saveOrUpdate(object);
		sessionFactory.getCurrentSession().flush();/* immediate flush for in-single-session operation */
	}
	
	public void update(T object) {
	
		sessionFactory.getCurrentSession().update(object);
		sessionFactory.getCurrentSession().flush();/* immediate flush for in-single-session operation */
	}
	
	public void delete(T object) {
	
		sessionFactory.getCurrentSession().delete(object);
		sessionFactory.getCurrentSession().flush();/* immediate flush for in-single-session operation */
	}
	
	/**
	 * Удаление объекта по идентификатору. Таблица определяется по классу.
	 *  
	 * @param clazz
	 * @param id
	 */
	public void deleteObject(Class<?> clazz, Serializable id) {

		String hql = String.format("DELETE FROM %s WHERE id = :id", clazz.getName());
		log.info("delete hql = {}", hql);
		sessionFactory.getCurrentSession()
						.createQuery(hql)
						.setParameter("id", id)
						.executeUpdate();
		sessionFactory.getCurrentSession()
						.flush();/* immediate flush for in-single-session operation */
	}
	
	@SuppressWarnings("unchecked")
	public <K> List<K> list(Class<K> clazz,
			Integer from,
			Integer count) {
	
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		setLimits(criteria, from, count);
		return (List<K>) criteria.list();
	}
	
	public <K> long count(Class<K> clazz) {
	
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).longValue();
	}
	
	protected Query setLimits(Query query,
			Integer from,
			Integer count) {
	
		if (from != null) {
			query.setFirstResult(from);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query;
	}
	
	protected Criteria setLimits(Criteria criteria,
			Integer from,
			Integer count) {
	
		if (from != null) {
			criteria.setFirstResult(from);
		}
		if (count != null) {
			criteria.setMaxResults(count);
		}
		return criteria;
	}
}
