package com.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchData {
	
	static SessionFactory sf = null;
	
	public static void main(String[] args) {
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		
		Query query = session.getNamedQuery("findEmployeeByName");
		query.setString("name", "trishu");
		
		
		List<Employee>  list = query.list();
		Iterator<Employee> it = list.iterator();
		while(it.hasNext()){
			Employee emp = it.next();
			System.out.println("Emp: emp id"+emp.getId()+" emp name"+emp.getName()+" emp salary"+emp.getSalary());
		}
		
		session.close();
		
		addEmployee();
	}
	
	public static void addEmployee(){
		System.out.println("Added new employee");
		Employee emp = new Employee();
		emp.setId(2);
		emp.setName("lolly");
		emp.setSalary(20000);
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.save(emp);
		
		session.getTransaction().commit();
		
		session.close();
		
		System.out.println("Employee added!");
	}

}
