package com.hub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hub.models.RadfordRecommendedReading;
import com.hub.models.StudentRecommendedReading;
import com.hub.services.RadfordRecommendedReadingService;
import com.hub.services.StudentRecommendedReadingService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/readingroom")
public class ReadingRoomController {
	
	@Autowired
	UserService userService; //Can be used however you want

	@Autowired
	RadfordRecommendedReadingService rrService;
	
	@Autowired
	StudentRecommendedReadingService srService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		List<RadfordRecommendedReading> rrcs = rrService.getAllRadfordRecommendedReadings();
		List<StudentRecommendedReading> srcs = srService.getAllStudentRecommendedReadings();
		
		model.addAttribute("radfordRecommendeds", rrcs);
		model.addAttribute("studentRecommendeds", srcs);
		
		return "readingroompage";
	}	
	
	@RequestMapping(value = "addRadfordReading", method = RequestMethod.POST)
	public String addRadfordRecommendedReading(ModelMap model, @RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "buy_link", required=true) String buy_link,
			@RequestParam(name = "download", required=true) String download,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "description", required=true) String description){
		RadfordRecommendedReading rrr = new RadfordRecommendedReading();
		rrr.setTitle(title);
		rrr.setBuyLink(buy_link);
		rrr.setDownload(download);
		rrr.setImage(image);
		rrr.setDescription(description);
		
		rrService.addRadfordRecommendedReading(rrr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "addStudentReading", method = RequestMethod.POST)
	public String addStudentRecommendedReading(ModelMap model, @RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "buy_link", required=true) String buy_link,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "description", required=true) String description){
		StudentRecommendedReading srr = new StudentRecommendedReading();
		srr.setTitle(title);
		srr.setBuyLink(buy_link);
		srr.setImage(image);
		srr.setDescription(description);
				
		srService.addStudentRecommendedReading(srr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "deleteRadfordRecommendation", method = RequestMethod.POST)
	public String deleteRadfordRecommendation(ModelMap model, @RequestParam(name = "id", required=true) int id){
		RadfordRecommendedReading rrr = rrService.getRadfordRecommendedReadingById(id);
		rrService.deleteRadfordRecommendedReading(rrr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "deleteStudentRecommendation", method = RequestMethod.POST)
	public String deleteStudentRecommendation(ModelMap model, @RequestParam(name = "id", required=true) int id){
		StudentRecommendedReading srr = srService.getStudentRecommendedReadingById(id);
		srService.deleteStudentRecommendedReading(srr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "updateRadfordReading/{id}", method = RequestMethod.POST)
	public String updateRadfordReading(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "buy_link", required=true) String buylink,
			@RequestParam(name = "download", required=true) String download,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "description", required=true) String description){
		RadfordRecommendedReading rr = rrService.getRadfordRecommendedReadingById(id);
		rr.setTitle(title);
		rr.setBuyLink(buylink);
		rr.setDescription(description);
		rr.setDownload(download);
		rr.setImage(image);
		
		rrService.updateRadfordRecommendedReading(rr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "updateStudentReading/{id}", method = RequestMethod.POST)
	public String updateStudentReading(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "buy_link", required=true) String buylink,
			@RequestParam(name = "image", required=true) String image,
			@RequestParam(name = "description", required=true) String description){
		StudentRecommendedReading sr = srService.getStudentRecommendedReadingById(id);
		sr.setTitle(title);
		sr.setBuyLink(buylink);
		sr.setImage(image);
		sr.setDescription(description);
		
		srService.updateStudentRecommendedReading(sr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "rrthumbsUp/{id}", method = RequestMethod.POST)
	public String rrThumbsUp(ModelMap model, @PathVariable("id") int id) {
		RadfordRecommendedReading rrr = rrService.getRadfordRecommendedReadingById(id);
		rrr.setThumbsUp(rrr.getThumbsUp() + 1);
		rrService.updateRadfordRecommendedReading(rrr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "rrthumbsDown/{id}", method = RequestMethod.POST)
	public String rrThumbsDown(ModelMap model, @PathVariable("id") int id) {
		RadfordRecommendedReading rrr = rrService.getRadfordRecommendedReadingById(id);
		rrr.setThumbsDown(rrr.getThumbsDown() + 1);
		rrService.updateRadfordRecommendedReading(rrr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "srthumbsUp/{id}", method = RequestMethod.POST)
	public String srThumbsUp(ModelMap model, @PathVariable("id") int id) {
		StudentRecommendedReading srr = srService.getStudentRecommendedReadingById(id);
		srr.setThumbsUp(srr.getThumbsUp() + 1);
		srService.updateStudentRecommendedReading(srr);
		
		return "redirect:/readingroom";
	}
	
	@RequestMapping(value = "srthumbsDown/{id}", method = RequestMethod.POST)
	public String srThumbsDown(ModelMap model, @PathVariable("id") int id) {
		StudentRecommendedReading srr = srService.getStudentRecommendedReadingById(id);
		srr.setThumbsDown(srr.getThumbsDown() + 1);
		srService.updateStudentRecommendedReading(srr);
		
		return "redirect:/readingroom";
	}
}
