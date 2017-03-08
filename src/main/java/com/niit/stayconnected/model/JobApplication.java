package com.niit.stayconnected.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "JobApplication")
@Component
public class JobApplication {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobAplnId;
	
	@Column
	private String mailid;
	
	@Column
	private int jobSid;
	
	@Column
	private char jobStatus;

	public int getJobAplnId() {
		return jobAplnId;
	}

	public void setJobAplnId(int jobAplnId) {
		this.jobAplnId = jobAplnId;
	}


	public int getJobSid() {
		return jobSid;
	}

	public void setJobSid(int jobSid) {
		this.jobSid = jobSid;
	}

	public char getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(char jobStatus) {
		this.jobStatus = jobStatus;
	}
	
}
