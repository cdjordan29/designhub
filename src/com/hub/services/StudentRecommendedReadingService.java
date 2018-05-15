package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.StudentRecommendedReadingDAO;
import com.hub.models.StudentRecommendedReading;

@Service
public class StudentRecommendedReadingService {

	@Autowired
	StudentRecommendedReadingDAO dao;
	
	public boolean addStudentRecommendedReading(StudentRecommendedReading srr){
		return dao.addStudentRecommendedReading(srr);
	}
	
	public StudentRecommendedReading getStudentRecommendedReadingById(int id){
		return dao.getStudentRecommendedReadingById(id);
	}
	
	public List<StudentRecommendedReading> getAllStudentRecommendedReadings(){
		return dao.getAllStudentRecommendedReadings();
	}
	
	public boolean updateStudentRecommendedReading(StudentRecommendedReading srr){
		return dao.updateStudentRecommendedReading(srr);
	}
	
	public boolean deleteStudentRecommendedReading(StudentRecommendedReading srr){
		return dao.deleteStudentRecommendedReading(srr);
	}
}
