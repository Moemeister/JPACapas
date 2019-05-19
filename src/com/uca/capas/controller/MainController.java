package com.uca.capas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uca.capas.dao.StudentDAO;
import com.uca.capas.domain.Student;

@Controller
public class MainController {
	static Logger log = Logger.getLogger(MainController.class.getName());
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain(){
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		 students = studentDao.findAll();
		 log.info("Termino de Buscar en la Base de Datos");
		}
		catch(Exception e){
			e.printStackTrace();
			log.log(Level.SEVERE,"Exception Occur");
		}
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/form")
	public ModelAndView main2(@RequestParam(value="code")Integer code ){
		ModelAndView mav = new ModelAndView();
			Student students = null;
			try {
				 students = studentDao.findOne(code);
				 log.info("Termino de Buscar por codigo en la Base de Datos");
				}
				catch(Exception e){
					e.printStackTrace();
					log.log(Level.SEVERE,"Exception Occur");
				}
				mav.addObject("students",students);
				mav.setViewName("form");
		return mav;
		
	}
	@RequestMapping (value="/save",method=RequestMethod.POST)
	public ModelAndView insert() {
		ModelAndView mav =  new ModelAndView();
		mav.addObject("student",new Student());
		mav.setViewName("guardar");
		return mav;
		
	}
	@RequestMapping ("/formData")
	ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			log.info("Agrego un nuevo usuario");
			studentDao.save(s, 1);
			
			
		}catch (Exception e) {
			log.info("Error: "+e.toString() );
		}
		students = studentDao.findAll();
		log.info(students.get(0).getlName());
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping (value= "/edit" , method = RequestMethod.POST)
	ModelAndView edit(@RequestParam(value = "code")int code) {
		ModelAndView mav = new ModelAndView();
		Student s = null;
		try {
			s = studentDao.findOne(code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("student", s);
		mav.setViewName("editar");

		
		return mav;
		
	}
	@RequestMapping("/editInfo")
	ModelAndView update(@ModelAttribute Student s) {
		List<Student> student = null;
		ModelAndView mav = new ModelAndView();
		try {
			log.info("Se actualizo un registro");
			studentDao.save(s, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		student = studentDao.findAll();
		log.info(student.get(0).getlName());
		mav.addObject("students",student);
		mav.setViewName("main");
		
		return mav;
	}
	@RequestMapping("/delete")
	ModelAndView delete(@RequestParam(value="name")String name) {
		List<Student> s = null;
		ModelAndView mav = new ModelAndView();
		try {
			studentDao.delete(name);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		s = studentDao.findAll();
		log.info(s.get(0).getlName());
		mav.addObject("students",s);
		mav.setViewName("main");
		
		return mav;
	}

}