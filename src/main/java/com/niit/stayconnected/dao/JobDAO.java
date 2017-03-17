package com.niit.stayconnected.dao;

import java.util.List;

import com.niit.stayconnected.model.Job;


public interface JobDAO {

	
void postJob(Job job);

List<Job> getAllJobs();

Job getJob(int id);

Job updateJob(int id,Job job);

void deleteJob(int id);


}

