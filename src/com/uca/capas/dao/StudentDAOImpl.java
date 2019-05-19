package com.uca.capas.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.controller.MainController;
import com.uca.capas.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

	@Override
	public List<Student> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.student");
		Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
		List<Student> resulset= query.getResultList();
		
		return resulset;
	}

	@Override
	public Student findOne(Integer code) throws DataAccessException {
		// TODO Auto-generated method stub
		Student student = entityManager.find(Student.class, code);
		return student;
	}
	static Logger log = Logger.getLogger(MainController.class.getName());
	@Transactional
	public int save(Student s, Integer newRow) throws DataAccessException {
		try {
			if(newRow == 1 ) entityManager.persist(s);
			else entityManager.merge(s);
			entityManager.flush();
			return 1;
		}catch (Throwable e){
			log.info("Error: "+e.toString() );
			return 1;
		}
	}

	@Transactional
	public int delete(String name) throws DataAccessException {
		try{
			StringBuffer sb= new StringBuffer();
			sb.append("delete from public.student where s_name=:name");
			Query query = entityManager.createNativeQuery(sb.toString(),Student.class);
			query.setParameter("name", name);
			query.executeUpdate();
			 return 1;
		} catch(Throwable e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}