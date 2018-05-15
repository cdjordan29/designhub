package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.TechniqueDAO;
import com.hub.models.Technique;
import com.hub.models.TechniqueSubCategory;

@Service
public class TechniqueService {

	@Autowired
	TechniqueDAO dao;
	
	public boolean addTechnique(Technique t){
		return dao.addTechnique(t);
	}
	
	public Technique getTechniqueById(int id){
		return dao.getTechniqueById(id);
	}
	
	public List<Technique> getAllTechniques(){
		return dao.getAllTechniques();
	}
	
	public List<Technique> getAllTechniquesInSubCategory(TechniqueSubCategory tsc){
		return dao.getAllTechniquesInSubCategory(tsc);
	}
	
	public boolean updateTechnique(Technique t){
		return dao.updateTechnique(t);
	}
	
	public boolean deleteTechnique(Technique t){
		return dao.deleteTechnique(t);
	}
}
