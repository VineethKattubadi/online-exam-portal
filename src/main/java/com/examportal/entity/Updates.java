package com.examportal.entity;

import java.util.Timer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="updates")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "updateId")
public class Updates {
	@Id
	@Column(name="update")
	private int updateId;
//	@Column(name="time_stamp")
	//private Timer timeStamp;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="admin_id")
	private Admin admin;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="test_id")
	private Test test;
	
	
	public Updates() {
		  
	}
	
	

}
