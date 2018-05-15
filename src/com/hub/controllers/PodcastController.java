package com.hub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hub.models.Podcast;
import com.hub.models.RadfordRecommendedCollaboration;
import com.hub.services.PodcastService;
import com.hub.services.UserService;

@Controller
@RequestMapping("/podcast")
public class PodcastController {
	
	@Autowired
	UserService userService; //Can be used however you want
	
	@Autowired
	PodcastService pcService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		List<Podcast> rrcs = pcService.getAllPodcasts();
				
		model.addAttribute("radfordRecommendeds", rrcs);
		
		return "podcastpage";
	}	
	
	@RequestMapping(value = "deletePodcast", method = RequestMethod.POST)
	public String deletePodcast(ModelMap model, @RequestParam(name = "id", required=true) int id){
		Podcast pc = pcService.getPodcastById(id);
		pcService.deletePodcast(pc);
		
		return "redirect:/podcast";
	}
	
	@RequestMapping(value = "addPodcast", method = RequestMethod.POST)
	public String addRadfordRecommendation(ModelMap model, @RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description,
			@RequestParam(name = "download", required=true) String download){
		Podcast pc = new Podcast();
		pc.setTitle(title);
		pc.setDescription(description);
		pc.setDownload(download);
		
		pcService.addPodcast(pc);
		
		return "redirect:/podcast";
	}
	
	@RequestMapping(value = "updatePodcast/{id}", method = RequestMethod.POST)
	public String updatePodcast(ModelMap model, @PathVariable("id") int id,
			@RequestParam(name = "title", required=true) String title,
			@RequestParam(name = "description", required=true) String description,
			@RequestParam(name = "download", required=true) String download){
		Podcast pc = pcService.getPodcastById(id);
		pc.setTitle(title);
		pc.setDescription(description);
		pc.setDownload(download);
		
		pcService.updatePodcast(pc);
		
		return "redirect:/podcast";
	}
}