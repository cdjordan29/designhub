package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.TechniqueSubCategoryDAO;
import com.hub.models.TechniqueCategory;
import com.hub.models.TechniqueSubCategory;

@Service
public class TechniqueSubCategoryService {

	@Autowired
	TechniqueSubCategoryDAO dao;
	
	public boolean addTechniqueSubCategory(TechniqueSubCategory t){
		return dao.addTechniqueSubCategory(t);
	}
	
	public TechniqueSubCategory getTechniqueSubCategoryById(int id){
		return dao.getTechniqueSubCategoryById(id);
	}
	
	public List<TechniqueSubCategory> getAllTechniqueSubCategories(){
		return dao.getAllTechniqueSubCategories();
	}
	
	public List<TechniqueSubCategory> getAllTechniqueSubCategorysInCategory(TechniqueCategory tc){
		return dao.getAllTechniqueSubCategoriesInCategory(tc);
	}
	
	public boolean updateTechniqueSubCategory(TechniqueSubCategory t){
		return dao.updateTechniqueSubCategory(t);
	}
	
	public boolean deleteTechniqueSubCategory(TechniqueSubCategory t){
		return dao.deleteTechniqueSubCategory(t);
	}
}
