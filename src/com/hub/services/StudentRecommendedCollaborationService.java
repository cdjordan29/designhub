package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.StudentRecommendedCollaborationDAO;
import com.hub.models.StudentRecommendedCollaboration;

@Service
public class StudentRecommendedCollaborationService {

	@Autowired
	StudentRecommendedCollaborationDAO dao;
	
	public boolean addStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		return dao.addStudentRecommendedCollaboration(src);
	}
	
	public StudentRecommendedCollaboration getStudentRecommendedCollaborationById(int id){
		return dao.getStudentRecommendedCollaborationById(id);
	}
	
	public List<StudentRecommendedCollaboration> getAllStudentRecommendedCollaborations(){
		return dao.getAllStudentRecommendedCollaborations();
	}
	
	public boolean updateStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		return dao.updateStudentRecommendedCollaboration(src);
	}
	
	public boolean deleteStudentRecommendedCollaboration(StudentRecommendedCollaboration src){
		return dao.deleteStudentRecommendedCollaboration(src);
	}
}
