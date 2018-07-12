package com.hcl.bootcamp.fs.springboot.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.bootcamp.fs.springboot.app.model.EventForm;
import com.hcl.bootcamp.fs.springboot.app.service.EventsService;

@CrossOrigin
@Controller
public class EventsController {
	private final Logger m_Logger = LoggerFactory.getLogger(this.getClass());
	private static final String m_Start = "START";
	private static final String m_End = "END";

	@Autowired
	private EventsService m_EventsService;

	@RequestMapping(value = { "/", "/screen2" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		final String i_MethodName = " @Controller:EventsController:welcome";
		m_Logger.debug(m_Start + i_MethodName);

		model.addAttribute("eventForm", new EventForm());
		model.addAttribute("events", m_EventsService.findAll());
		m_Logger.debug(m_End + i_MethodName);
		return "screen2";
	}

}