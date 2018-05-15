package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.TechniqueCategoryDAO;
import com.hub.models.TechniqueCategory;

@Service
public class TechniqueCategoryService {

	@Autowired
	TechniqueCategoryDAO dao;
	
	public boolean addTechniqueCategory(TechniqueCategory cb){
		return dao.addTechniqueCategory(cb);
	}
	
	public TechniqueCategory getTechniqueCategoryById(int id){
		return dao.getTechniqueCategoryById(id);
	}
	
	public List<TechniqueCategory> getAllTechniqueCategorys(){
		return dao.getAllTechniqueCategories();
	}
	
	public boolean updateTechniqueCategory(TechniqueCategory cb){
		return dao.updateTechniqueCategory(cb);
	}
	
	public boolean deleteTechniqueCategory(TechniqueCategory cb){
		return dao.deleteTechniqueCategory(cb);
	}
}
