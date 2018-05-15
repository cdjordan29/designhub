package com.hub.controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hub.models.CommunityBulletin;
import com.hub.models.CommunityEvent;
import com.hub.services.CommunityBulletinService;
import com.hub.services.CommunityEventService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	CommunityBulletinService cbService;
	
	@Autowired
	CommunityEventService ceService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		List<CommunityBulletin> cb = cbService.getAllCommunityBulletins();
		List<CommunityEvent> ce = ceService.getAllCommunityEvents();
		
		model.addAttribute("communityBulletins", cb);
		model.addAttribute("communityEvents", ce);
		
		return "communitypage";
	}
	
	@RequestMapping(value = "addBulletin", method = RequestMethod.POST)
	public String addBulletin(ModelMap model, @RequestParam(name = "description", required=true) String description){
		CommunityBulletin cb = new CommunityBulletin();
		cb.setDescription(description);
		cbService.addCommunityBulletin(cb);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "addEvent", method = RequestMethod.POST)
	public String addEvent(ModelMap model, @RequestParam(name = "date", required=true) Date date,
			@RequestParam(name = "description", required=true) String description){
		CommunityEvent ce = new CommunityEvent();
		ce.setDate(date);
		ce.setDescription(description);
		
		ceService.addCommunityEvent(ce);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "deleteBulletin", method = RequestMethod.POST)
	public String deleteBulletin(ModelMap model, @RequestParam(name = "id", required=true) int id){
		CommunityBulletin cb = cbService.getCommunityBulletinById(id);
		cbService.deleteCommunityBulletin(cb);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "deleteEvent", method = RequestMethod.POST)
	public String deleteEvent(ModelMap model, @RequestParam(name = "id", required=true) int id){
		CommunityEvent ce = ceService.getCommunityEventById(id);
		ceService.deleteCommunityEvent(ce);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "thumbsUp/{id}", method = RequestMethod.POST)
	public String thumbsUp(ModelMap model, @PathVariable("id") int id) {
		CommunityBulletin cb = cbService.getCommunityBulletinById(id);
		cb.setThumbsUp(cb.getThumbsUp() + 1);
		cbService.updateCommunityBulletin(cb);
		
		return "redirect:/community";
	}
	
	@RequestMapping(value = "thumbsDown/{id}", method = RequestMethod.POST)
	public String thumbsDown(ModelMap model, @PathVariable("id") int id) {
		CommunityBulletin cb = cbService.getCommunityBulletinById(id);
		cb.setThumbsDown(cb.getThumbsDown() + 1);
		cbService.updateCommunityBulletin(cb);
		
		return "redirect:/community";
	}
}