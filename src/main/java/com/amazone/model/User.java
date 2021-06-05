package com.amazone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "userdetails")
public class User {

	@Id
	@Column(name="userid")
	String userId;
	String name;
	String password;
	String mailid;
	Integer mobileno;
	String address;
	Double wallet;
	
}
