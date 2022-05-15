package com.example.demo.rest;


import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.serviceImpl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/user")

public class RestControllerClass {
	
	    @Autowired
	    private ServiceImpl service;
	
	    //1. create company
		@PostMapping("/create")
		public ResponseEntity<String> createUser(
		           @Valid  @RequestBody User user
				) 
		{
			log.info("ENTERED INTO SAVE METHOD");
			ResponseEntity<String> response = null;
			try {
				Integer id = service.createUser(user);
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
		public ResponseEntity<List<User>> getAllUsers() {
			ResponseEntity<List<User>> response = null;
			log.info("ENTERED INTO FETCH METHOD");
			try {
				List<User> list = service.getAllUsers();
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
		public ResponseEntity<User> getOneUser(
				@PathVariable Integer id
				)
		{
			ResponseEntity<User> response = null;
			log.info("ENTERED INTO FETCH ONE METHOD");
			try {
				User user = service.getOneUser(id);
				response = ResponseEntity.ok(user);
				log.info("FETCH ONE METHOD IS SUCCESS");
			} catch (UserNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
			log.info("ABOUT TO LEAVE FETCH ONE METHOD");
			return response;
		}
		
		        //4. Deposit method
				@PutMapping("/deposit/{id}/{amount}")
				public ResponseEntity<String> getDeposit(
						@PathVariable Integer id,@PathVariable Double amount
						)
				{
					ResponseEntity<String> response = null;
					log.info("ENTERED INTO FETCH ONE METHOD");
					try {
						//User user = service.getOneUser(id);
						String message=service.getDeposit(id, amount);
						//System.out.println(user.getBalance());
						//user.deposit(amount);
						response = ResponseEntity.ok(message);
						log.info("FETCH ONE METHOD IS SUCCESS");
					} catch (UserNotFoundException e) {
						log.error(e.getMessage());
						throw e;
					}
					log.info("ABOUT TO LEAVE FETCH ONE METHOD");
					return response;
				}
				
				//5. WithDraw method
				@PutMapping("/withdraw/{id}/{amount}")
				public ResponseEntity<String> getWithdraw(
						@PathVariable Integer id,@PathVariable Double amount
						)
				{
					ResponseEntity<String> response = null;
					log.info("ENTERED INTO FETCH ONE METHOD");
					try {
						//User user = service.getOneUser(id);
						String message=service.getWithdraw(id, amount);
						//System.out.println(user.getBalance());
						//user.withdraw(amount);
						
						response = ResponseEntity.ok(message);
						log.info("FETCH ONE METHOD IS SUCCESS");
					} catch (UserNotFoundException e) {
						log.error(e.getMessage());
						throw e;
					}
					log.info("ABOUT TO LEAVE FETCH ONE METHOD");
					return response;
				}
				
				//6.Transfer Money
				@PutMapping("/transfer/{id1}/{id2}/{amount}")
				public ResponseEntity<String> getTransfer(
						@PathVariable Integer id1,@PathVariable Integer id2,@PathVariable Double amount
						)
				{
					ResponseEntity<String> response = null;
					log.info("ENTERED INTO GET TRANSFER METHOD");
					try {
						//User user1 = service.getOneUser(id1);
						//User user2 = service.getOneUser(id2);
						String message=service.getTransfer(id1,id2, amount);
					//	System.out.println(user1.getBalance());
						//user1.transferamount(user2,amount);
						
						response = ResponseEntity.ok(message);
						log.info("GET TRANSFER METHOD IS SUCCESS");
					} catch (UserNotFoundException e) {
						log.error(e.getMessage());
						throw e;
					}
					log.info("ABOUT TO LEAVE FETCH ONE METHOD");
					return response;
				}
				//delete user
				@DeleteMapping("/remove/{id}")
				public ResponseEntity<String> removeProduct(
						@PathVariable Integer id
						) 
				{
					ResponseEntity<String> resp = null;
					try {
						service.removeUser(id);
						resp = new ResponseEntity<String>(
								"Product Removed " +id ,
								HttpStatus.OK);
					} catch (UserNotFoundException  e) {
						e.printStackTrace();
						resp = new ResponseEntity<String>(
								e.getMessage(),
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return resp;
				}
				
				//Update user
				@PutMapping("/update/{id}")
				public ResponseEntity<String> updateProduct(
						@PathVariable Integer id,@RequestBody User user
						) 
				{
					System.out.println(id);
					ResponseEntity<String> resp = null;
					try {
						String message=service.updateUser(user, id);
						resp = new ResponseEntity<String>(
								"Product updated " +id ,
								HttpStatus.OK);
					} catch (UserNotFoundException  e) {
						e.printStackTrace();
						resp = new ResponseEntity<String>(
								e.getMessage(),
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return resp;
			/*
					ResponseEntity<String> resp = null;
					try {
						Integer id1=service.updateUser(user);
						resp = new ResponseEntity<String>(
								"Product updated " +id1 ,
								HttpStatus.OK);
					} catch (UserNotFoundException  e) {
						e.printStackTrace();
						resp = new ResponseEntity<String>(
								e.getMessage(),
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return resp;*/
				}
				
				//Not Required
				//@PutMapping("/account/{use_id}/{amount}")
				@PutMapping("/account")
				public void depositAmount(@RequestBody User user) {
					//int initBal = getBalance(acctID);
					service.getDeposit(user);
					//Logger logger = new Logger(acctID, "Deposited", "Success", initBal, initBal + amount);
					//loggerController.addLog(logger);
				}//not required
				
				


 
}
