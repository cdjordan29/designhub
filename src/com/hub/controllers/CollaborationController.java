package com.hub.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hub.models.RadfordRecommendedCollaboration;
import com.hub.models.StudentRecommendedCollaboration;
import com.hub.services.RadfordRecommendedCollaborationService;
import com.hub.services.StudentRecommendedCollaborationService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/collaboration")
public class CollaborationController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	RadfordRecommendedCollaborationService rrcService;
	
	@Autowired
	StudentRecommendedCollaborationService srcService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		List<RadfordRecommendedCollaboration> rrcs = rrcService.getAllRadfordRecommendedCollaborations();
		List<StudentRecommendedCollaboration> srcs = srcService.getAllStudentRecommendedCollaborations();
		
		model.addAttribute("radfordRecommendeds", rrcs);
		model.addAttribute("studentRecommendeds", srcs);
		
		return "collaborationpage";
	}	
	
	@RequestMapping(value = "deleteRadfordRecommendation", method = RequestMethod.POST)
	public String deleteRadfordRecommendation(ModelMap model, @RequestParam(name = "id", required=true) int id){
		RadfordRecommendedCollaboration rrc = rrcService.getRadfordRecommendedCollaborationById(id);
		rrcService.deleteRadfordRecommendedCollaboration(rrc);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "addRadfordRecommendation", method = RequestMethod.POST)
	public String addRadfordRecommendation(ModelMap model, @RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description, 
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "download", required=true) String download){
		RadfordRecommendedCollaboration rrc = new RadfordRecommendedCollaboration();
		rrc.setTitle(title);
		rrc.setDescription(description);
		rrc.setImage(image);
		rrc.setDownload(download);
		
		rrcService.addRadfordRecommendedCollaboration(rrc);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "updateRadfordRecommendation/{id}", method = RequestMethod.POST)
	public String updateRadfordRecommendation(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "download", required=true) String download){
		RadfordRecommendedCollaboration rrc = rrcService.getRadfordRecommendedCollaborationById(id);
		rrc.setTitle(title);
		rrc.setDescription(description);
		rrc.setImage(image);
		rrc.setDownload(download);
		
		rrcService.updateRadfordRecommendedCollaboration(rrc);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "deleteStudentRecommendation", method = RequestMethod.POST)
	public String deleteStudentRecommendation(ModelMap model, @RequestParam(name = "id", required=true) int id){
		StudentRecommendedCollaboration src = srcService.getStudentRecommendedCollaborationById(id);
		srcService.deleteStudentRecommendedCollaboration(src);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "addStudentRecommendation", method = RequestMethod.POST)
	public String addStudentRecommendation(ModelMap model, @RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "download", required=true) String download){
		StudentRecommendedCollaboration src = new StudentRecommendedCollaboration();
		src.setTitle(title);
		src.setDescription(description);
		src.setImage(image);
		src.setDownload(download);
		
		srcService.addStudentRecommendedCollaboration(src);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "updateStudentRecommendation/{id}", method = RequestMethod.POST)
	public String updateStudentRecommendation(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "download", required=true) String download){
		StudentRecommendedCollaboration rrc = srcService.getStudentRecommendedCollaborationById(id);
		rrc.setTitle(title);
		rrc.setDescription(description);
		rrc.setImage(image);
		rrc.setDownload(download);
		
		srcService.updateStudentRecommendedCollaboration(rrc);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "thumbsUp/{id}", method = RequestMethod.POST)
	public String thumbsUp(ModelMap model, @PathVariable("id") int id) {
		StudentRecommendedCollaboration src = srcService.getStudentRecommendedCollaborationById(id);
		src.setThumbsUp(src.getThumbsUp() + 1);
		srcService.updateStudentRecommendedCollaboration(src);
		
		return "redirect:/collaboration";
	}
	
	@RequestMapping(value = "thumbsDown/{id}", method = RequestMethod.POST)
	public String thumbsDown(ModelMap model, @PathVariable("id") int id) {
		StudentRecommendedCollaboration src = srcService.getStudentRecommendedCollaborationById(id);
		src.setThumbsDown(src.getThumbsDown() + 1);
		srcService.updateStudentRecommendedCollaboration(src);
		
		return "redirect:/collaboration";
	}
}
