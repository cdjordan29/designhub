package com.hub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.ReminderFrequencyDAO;
import com.hub.models.ReminderFrequency;

@Service
public class ReminderFrequencyService {

	@Autowired
	ReminderFrequencyDAO dao;
	
	public ReminderFrequency findReminderFrequencyById(long id) {
		return dao.findReminderFrequencyById(id);
	}
}
