package com.hcl.bootcamp.fs.springboot.app.service;

import java.util.ArrayList;
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
	private static final String m_Start = "START";
	private static final String m_End = "END";
	
	@Autowired
	EventsRepository m_EventsRepository;
	
	public List<EventVO> findAll(){
		final String i_MethodName = " jpa:IEventsService:findAll";
		m_Logger.debug(m_Start + i_MethodName);
		List<EventVO> i_EventVOList = new ArrayList<EventVO>();
		i_EventVOList = m_EventsRepository.findAll();
		m_Logger.debug(m_End + i_MethodName);
		return i_EventVOList;
	}
	
	public EventVO findById(Integer id) {
		final String i_MethodName = " jpa:IEventsService:findById";
		m_Logger.debug(m_Start + i_MethodName);
		Optional<EventVO> i_OptionalEventVO = m_EventsRepository.findById(new Long(id));
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
}
