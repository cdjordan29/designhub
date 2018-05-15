package com.hub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.RadfordRecommendedReadingDAO;
import com.hub.models.RadfordRecommendedReading;

@Service
public class RadfordRecommendedReadingService {

	@Autowired
	RadfordRecommendedReadingDAO dao;
	
	public boolean addRadfordRecommendedReading(RadfordRecommendedReading rrr){
		return dao.addRadfordRecommendedReading(rrr);
	}
	
	public RadfordRecommendedReading getRadfordRecommendedReadingById(int id){
		return dao.getRadfordRecommendedReadingById(id);
	}
	
	public List<RadfordRecommendedReading> getAllRadfordRecommendedReadings(){
		return dao.getAllRadfordRecommendedReadings();
	}
	
	public boolean updateRadfordRecommendedReading(RadfordRecommendedReading rrr){
		return dao.updateRadfordRecommendedReading(rrr);
	}
	
	public boolean deleteRadfordRecommendedReading(RadfordRecommendedReading rrr){
		return dao.deleteRadfordRecommendedReading(rrr);
	}
}
