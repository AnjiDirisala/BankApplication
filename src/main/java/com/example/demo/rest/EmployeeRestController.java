package com.example.demo.rest;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.serviceImpl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class EmployeeRestController {
	    @Autowired
	    private ServiceImpl service;
	
	    //1. create Employee
		@PostMapping("/create")
		public ResponseEntity<String> createUser(
				@RequestBody Employee employee
				) 
		{
			log.info("ENTERED INTO SAVE METHOD");
			ResponseEntity<String> response = null;
			try {
				Integer id = service.createEmployee(employee);
				response = ResponseEntity.ok("CREATED WITH ID : "+ id);
				log.info("User IS CREATED {}.",id);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				
			}
			log.info("ABOUT TO LEAVE SAVE METHOD");
			return response;
		}
		
		      //2. fetch all
				@GetMapping("/all")
				public ResponseEntity<List<Employee>> getAllEmployees() {
					ResponseEntity<List<Employee>> response = null;
					log.info("ENTERED INTO FETCH METHOD");
					try {
						List<Employee> list = service.getAllEmployees();
						response = ResponseEntity.ok(list);
						log.info("FETCH METHOD IS SUCCESS");
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
					log.info("ABOUT TO LEAVE FETCH ALL METHOD");
					return response;
				}
				

				//3. fetch one
				@GetMapping("/fetch/{id}")
				public ResponseEntity<Employee> getOneEmployee(
						@PathVariable Integer id
						)
				{
					ResponseEntity<Employee> response = null;
					log.info("ENTERED INTO FETCH ONE METHOD");
					try {
						Employee employee = service.getOneEmployee(id);
						response = ResponseEntity.ok(employee);
						log.info("FETCH ONE METHOD IS SUCCESS");
					} catch (UserNotFoundException e) {
						log.error(e.getMessage());
						throw e;
					}
					log.info("ABOUT TO LEAVE FETCH ONE METHOD");
					return response;
				}
				

		
}
