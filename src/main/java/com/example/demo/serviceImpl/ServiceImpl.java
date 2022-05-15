package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.BankRepository;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.ServiceClass;


@Service
public class ServiceImpl implements ServiceClass {
	@Autowired
	private BankRepository repo;
	
	@Autowired
	private EmployeeRepository repo1;
	
	public Integer createUser(User user) {
		return repo.save(user).getId();
	}
	
	public Integer createEmployee(Employee employee) {
		return repo1.save(employee).getId();
	}
	
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	public List<Employee> getAllEmployees() {
		return repo1.findAll();
	}
	
	public User getOneUser(Integer id) {
		return 
		repo.findById(id).orElseThrow(
				()->new UserNotFoundException("No User Exist with Given id")
				);
		
	}
	
	public Employee getOneEmployee(Integer id) {
		return 
		repo1.findById(id).orElseThrow(
				()->new UserNotFoundException("No Employee Exist with Given id")
				);
		
	}
	public String getDeposit(Integer id,Double amount) {
		
		User user=getOneUser(id);
		user.deposit(amount);
		repo.save(user);
		return "Successful"; 
		
	}
	
	public String getWithdraw(Integer id,Double amount) {
		
		User user=getOneUser(id);
		user.withdraw(amount);
		repo.save(user);
		return "Successful"; 
	
	}
	public String getTransfer(Integer id1,Integer id2,Double amount) {
		User user1=getOneUser(id1);
		User user2=getOneUser(id2);
		user1.transferamount(user2,amount);
		repo.save(user1);
		repo.save(user2);
		return "Successful"; 
		
	}
	
	
	//no use
	public void getDeposit(User user) {
		//repo.saveBalanceByAcctID(user_id);
		repo.save(user);
	}

	public void removeUser(Integer id) {

		repo.deleteById(id);
		
	}

	public String updateUser(User user,Integer id) {
		User user1 = getOneUser(id);
		System.out.println(id);
		
		//user1.setId(user.getId());
		if(user.getName()!=null) {
		user1.setName(user.getName());}
		if(user.getPassword()!=null) {
		user1.setPassword(user.getPassword());
		}
		if(user.getAccnumber()!=null) {
		user1.setAccnumber(user.getAccnumber());
		}
		if(user.getAcctype()!=null) {
		user1.setAcctype(user.getAcctype());
		}
		if(user.getBalance()!=null) {
		user1.setBalance(user.getBalance());
		}
		User updateduser=repo.save(user1);
		return "successful";
		
	}

	

	

}
