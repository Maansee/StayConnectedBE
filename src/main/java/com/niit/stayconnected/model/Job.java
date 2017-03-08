package com.niit.stayconnected.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "Job")
@Component
public class Job {

	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobId;
	
	@Column
	private String jobTitle;
	
	@Column
	private String jobDescription;
	
	@Column
	private Date jobDate;
	
	@Column
	private String jobSkills;
	
	@Column
	private String jobEmail;
	

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobSkills() {
		return jobSkills;
	}

	public void setJobSkills(String jobSkills) {
		this.jobSkills = jobSkills;
	}

	public String getJobEmail() {
		return jobEmail;
	}

	public void setJobEmail(String jobEmail) {
		this.jobEmail = jobEmail;
	}

	
	
}
