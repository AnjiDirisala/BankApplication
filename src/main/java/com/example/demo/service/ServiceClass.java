package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;



public interface ServiceClass {
	public User getOneUser(Integer id);
	public List<User> getAllUsers();
	public Integer createUser(User user); 
	public String getDeposit(Integer id,Double amount);
	public String getWithdraw(Integer id,Double amount);
	
	public Integer createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getOneEmployee(Integer id);
	public void removeUser(Integer id) ;
	public String updateUser(User user,Integer id);
}
