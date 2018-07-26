package com.hcl.bootcamp.fs.springboot.app.service;

import java.util.List;

import com.hcl.bootcamp.fs.springboot.app.model.EventVO;

public interface IEventsService {
	/**
	 * Gets all events in database
	 * @return - List of Events
	 */
	public List<EventVO> findAll();
	/**
	 * Gets  event details from database by its ID
	 * @param id - Events ID
	 * @return - Event
	 */
	public EventVO findById(Long id);

	/**
	 * Saves new event in the database
	 * @param pEventVO
	 */
	public void save(EventVO pEventVO);
	
	/**
	 * Deletes new event from the database
	 * @param pEventVO
	 */
	public void delete(EventVO pEventVO);	
	
	public void update(EventVO pEventVO);	
	
	public List<Object[]> findAllUsersForListing(Long id);
	
}
