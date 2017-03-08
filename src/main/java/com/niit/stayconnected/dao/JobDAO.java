package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.Job;
import com.niit.stayconnected.model.JobApplication;

public interface JobDAO {

	public boolean postJob(Job job);
	
	public boolean updateJob(Job job);
	
	public List<Job> getAllJobs();
	
	public boolean ApplyJob(JobApplication jobappln);
	
	public boolean updateJobApplication(JobApplication jobappln);
	
	public JobApplication getJobApplication(String usermail, int jobSid);

	public List<JobApplication> listAllAppliedJobs(String usermail);
}
