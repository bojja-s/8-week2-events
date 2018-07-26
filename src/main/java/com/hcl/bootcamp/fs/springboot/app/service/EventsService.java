package com.hcl.bootcamp.fs.springboot.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bootcamp.fs.springboot.app.jpa.EventsRepository;
import com.hcl.bootcamp.fs.springboot.app.model.EventVO;

@Service("eventsService")
public class EventsService implements IEventsService {
	
	private final Logger m_Logger = LoggerFactory.getLogger(this.getClass());
	public static final String m_Start = "START";
	public static final String m_End = "END";
	public static final String m_Status_New = "NEW";

	public static final String m_Status_Joined = "Joined";

	public static final String m_Status_Edit_Action = "Edit";
	
	public static final String m_Status_Deleted = "Deleted";

	
	@Autowired
	EventsRepository m_EventsRepository;
	
	public List<EventVO> findAll(){
		final String i_MethodName = " jpa:IEventsService:findAll";
		m_Logger.debug(m_Start + i_MethodName);
		List<EventVO> i_EventVOList = m_EventsRepository.findAll();
		m_Logger.debug(m_End + i_MethodName);
		return i_EventVOList;
	}
	
	public EventVO findById(Long id) {
		final String i_MethodName = " jpa:IEventsService:findById";
		m_Logger.debug(m_Start + i_MethodName);
		Optional<EventVO> i_OptionalEventVO = m_EventsRepository.findById( id);
		m_Logger.debug(m_End + i_MethodName);
		return i_OptionalEventVO.get();
	}

	public void save(EventVO pEventVO) {
		final String i_MethodName = " jpa:IEventsService:save";
		m_Logger.debug(m_Start + i_MethodName);		
		m_EventsRepository.save(pEventVO);
		m_Logger.debug(m_End + i_MethodName);
	}
	
	public void delete(EventVO pEventVO) {
		final String i_MethodName = " jpa:IEventsService:delete";
		m_Logger.debug(m_Start + i_MethodName);			
		m_EventsRepository.delete(pEventVO);
		m_Logger.debug(m_End + i_MethodName);
	}
	public List<EventVO> findByName(String pName) {
		final String i_MethodName = " jpa:IEventsService:findByName";
		m_Logger.debug(m_Start + i_MethodName);
		List<EventVO> i_EventVOList = m_EventsRepository.findByName(pName);
		m_Logger.debug(m_End + i_MethodName);
		return i_EventVOList;
	}
	public void update(EventVO pEventVO) {
		final String i_MethodName = " jpa:IEventsService:update";
		m_Logger.debug(m_Start + i_MethodName);
		System.out.println("pEventVO " + pEventVO);
		m_EventsRepository.update(pEventVO.getId(),pEventVO.getName());
		m_Logger.debug(m_End + i_MethodName);
	}
	public List<Object[]> findAllUsersForListing(Long id){
		final String i_MethodName = " jpa:IEventsService:findAllUsersForListing";
		m_Logger.debug(m_Start + i_MethodName);		
		List<Object[]> i_AllUsersForListing = m_EventsRepository.findAllUsersForListing(id);
		m_Logger.debug(m_End + i_MethodName);
		return i_AllUsersForListing;
	}
}
