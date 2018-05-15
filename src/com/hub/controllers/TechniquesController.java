package com.hub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hub.models.TechniqueCategory;
import com.hub.models.TechniqueSubCategory;
import com.hub.models.Technique;
import com.hub.services.TechniqueCategoryService;
import com.hub.services.TechniqueSubCategoryService;
import com.hub.services.TechniqueService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/techniques")
public class TechniquesController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	TechniqueCategoryService tcService;
	
	@Autowired
	TechniqueSubCategoryService tscService;
	
	@Autowired
	TechniqueService tService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		List<TechniqueCategory> tc = tcService.getAllTechniqueCategorys();
		List<TechniqueSubCategory> tsc = tscService.getAllTechniqueSubCategories();
		List<Technique> t = tService.getAllTechniques();
		
		model.addAttribute("techniqueCategorys", tc);
		model.addAttribute("techniqueSubCategories", tsc);
		model.addAttribute("techniques", t);
		
		return "techniquespage";
	}	
	
	@RequestMapping(value = "deleteTechniqueCategory", method = RequestMethod.POST)
	public String deleteTechniqueCategory(ModelMap model, @RequestParam(name = "id", required=true) int id){
		TechniqueCategory tc = tcService.getTechniqueCategoryById(id);
		tcService.deleteTechniqueCategory(tc);
		
		return "redirect:/techniques";
	}
	
	@RequestMapping(value = "addTechniqueCategory", method = RequestMethod.POST)
	public String addTechniqueCategory(ModelMap model, @RequestParam(name = "category", required=true) String category){
		TechniqueCategory tc = new TechniqueCategory();
		tc.setName(category);
		
		tcService.addTechniqueCategory(tc);
		
		return "redirect:/techniques";
	}
	
	@RequestMapping(value = "deleteTechniqueSubCategory", method = RequestMethod.POST)
	public String deleteTechniqueSubCategory(ModelMap model, @RequestParam(name = "id", required=true) int id){
		TechniqueSubCategory tsc = tscService.getTechniqueSubCategoryById(id);
		tscService.deleteTechniqueSubCategory(tsc);
		
		return "redirect:/techniques";
	}
	
	@RequestMapping(value = "addTechniqueSubCategory", method = RequestMethod.POST)
	public String addTechniqueSubCategory(ModelMap model, @RequestParam(name = "name", required=true) String name,
			@RequestParam(name="categoryId", required=true) int categoryId, @RequestParam(name= "description", required=true) String description){
		TechniqueSubCategory tsc = new TechniqueSubCategory();
		tsc.setName(name);
		tsc.setDescription(description);
		tsc.setCategoryId(categoryId);
		
		tscService.addTechniqueSubCategory(tsc);
		
		return "redirect:/techniques";
	}

	@RequestMapping(value = "updateTechniqueSubCategory/{id}", method = RequestMethod.POST)
	public String updateStudentRecommendation(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "name", required=true) String name,
			@RequestParam(name = "description", required=true) String description){
		TechniqueSubCategory tsc = tscService.getTechniqueSubCategoryById(id);
		tsc.setName(name);
		tsc.setDescription(description);
		
		tscService.updateTechniqueSubCategory(tsc);
		
		return "redirect:/techniques";
	}
}
