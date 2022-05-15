package com.example.demo.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message = "Name can not be null")
	@Size(min = 3, max = 20, message = "Name must be 3-20 size only")
	@Column(name = "name")
	private String name;
	

	@Column(name = "password")
	private String password;
	
	@NotNull(message = "Please enter id")
	@Column(name = "accnumber")
	private Long accnumber;
	
	@NotBlank(message = "TYpe can not be null")
	@Size(min = 3, max = 20, message = "Type must be 3-20 size only")
	@Column(name = "acctype")
	private String acctype;
	
	@NotNull(message = "Please enter id")
	@Column(name = "balance")
	private Double balance;
	
	public void deposit(double amount) {
		  this.balance+=amount;
	  }
	  
	public void withdraw(double amount) {
		  this.balance-=amount;
	  }
	
	public void transferamount(User destUser,double amount) {
		
		  this.balance-=amount;
		  destUser.balance+=amount;
	  }

}
