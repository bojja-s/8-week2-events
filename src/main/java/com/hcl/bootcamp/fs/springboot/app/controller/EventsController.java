package com.hcl.bootcamp.fs.springboot.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hcl.bootcamp.fs.springboot.app.model.EventForm;
import com.hcl.bootcamp.fs.springboot.app.model.EventVO;
import com.hcl.bootcamp.fs.springboot.app.service.EventsService;
import com.hcl.bootcamp.fs.springboot.app.service.UserDetailsHeper;
import com.hcl.bootcamp.fs.springboot.app.validator.EventValidator;

@CrossOrigin
@Controller
public class EventsController {
	private final Logger m_Logger = LoggerFactory.getLogger(this.getClass());
	private static final String m_Start = "START";
	private static final String m_End = "END";

	@Autowired
	private EventsService m_EventsService;

	@Autowired
	private EventValidator m_EventValidator;
	
	private void processEventsByStateAndStatus( Model model) {
		List<EventVO> i_EventVOListOfOwnState = new ArrayList<EventVO>();
		List<EventVO> i_EventVOListOfOtherStates = new ArrayList<EventVO>();
		
		List<EventVO> i_EventVOList = m_EventsService.findAll();
		String i_User = UserDetailsHeper.findLoggedInUsername();
		for (EventVO i_EventVO:i_EventVOList) {
			System.out.println("*i_EventVO: " + i_EventVO);
			if ( i_EventVO.getState() != null && i_EventVO.getState().equals(i_EventVO.getUser().getLocation())){//Own state
				if ( i_EventVO.getHost() != null && i_EventVO.getHost().equals(i_User)){ //Host events
					String actions[] = {"Edit","Delete"};
					i_EventVO.setActions(actions);			
					i_EventVOListOfOwnState.add(i_EventVO);
					System.out.println("Own State Event");
				}else { // Others events
					if ( i_EventVO.getStatus() != null && i_EventVO.getStatus().equals(EventsService.m_Status_New)){
						String actions[] = {"Join",""};
						i_EventVO.setActions(actions);
						System.out.println("Own State Event");
						i_EventVOListOfOwnState.add(i_EventVO);
					}else if ( i_EventVO.getStatus() != null && i_EventVO.getStatus().equals(EventsService.m_Status_Joined)){
						String actions[] = {"Joining","Cancel"};
						i_EventVO.setActions(actions);	
						i_EventVOListOfOwnState.add(i_EventVO);
						
						System.out.println("Own State Event");
					}
				}
			}else {
				if ( i_EventVO.getStatus() != null && i_EventVO.getStatus().equals(EventsService.m_Status_New)){
					String actions[] = {"Join"};
					i_EventVO.setActions(actions);			
					i_EventVOListOfOtherStates.add(i_EventVO);
					System.out.println("Other State Event");
				}else {
					String actions[] = {"Joining","Cancel"};
					i_EventVO.setActions(actions);			
					i_EventVOListOfOtherStates.add(i_EventVO);
					System.out.println("Other State Event");					
				}
			}
		}
		model.addAttribute("hoststateevents", i_EventVOListOfOwnState);
		model.addAttribute("otherstatesevents", i_EventVOListOfOtherStates);		
	}
	@RequestMapping(value = { "/", "/screen2" }, method = RequestMethod.GET)
	public String welcome(Model model) {		
		final String i_MethodName = " @Controller:EventsController:welcome";
		m_Logger.debug(m_Start + i_MethodName);
		processEventsByStateAndStatus(model);
		m_Logger.debug(m_End + i_MethodName);
		return "screen2";
	}
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public String event(@ModelAttribute("eventForm") EventForm pEventForm, BindingResult bindingResult,Model model, final RedirectAttributes redirectAttributes) {
		final String i_MethodName = " @Controller:EventsController:events";
		System.out.println(m_Start + i_MethodName);
		m_Logger.debug(m_Start + i_MethodName);

		m_EventValidator.validate(pEventForm, bindingResult);
		if (bindingResult.hasErrors()) {
			m_Logger.debug("Validation errors");
			System.out.println("Validation errors");
			return "screen2";
		}		
		EventVO i_EventVO = toEventVO(pEventForm);
		i_EventVO.setHost( UserDetailsHeper.findLoggedInUsername() );
		i_EventVO.setStatus(EventsService.m_Status_New);
		m_EventsService.save(i_EventVO);
		redirectAttributes.addFlashAttribute("msg", "Event added successfully!");
		
		processEventsByStateAndStatus(model);
		
		m_Logger.debug(m_End + i_MethodName);
		System.out.println(m_End + i_MethodName);
		return "redirect:/screen2";
	}

	@RequestMapping(value = "/events/{id}/Edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model,final RedirectAttributes redirectAttributes) {
		System.out.println("EDIT() : {}"+ id);
		EventVO i_EventVO = m_EventsService.findById(id);
		System.out.println(i_EventVO);
		model.addAttribute("editevent", i_EventVO);
	
		return "screen3";
	}
	@RequestMapping(value = "/events/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("eventForm") EventForm pEventForm, BindingResult bindingResult,Model model, final RedirectAttributes redirectAttributes) {
		System.out.println("update() : {}"+ pEventForm);
		EventVO i_EventVO = toEventVO(pEventForm);
		m_EventsService.update(i_EventVO);
		return "redirect:/screen2";
	}
	@RequestMapping(value = "/events/{id}/Join", method = RequestMethod.GET)
	public String join(@PathVariable("id") Long id,@ModelAttribute("eventForm") EventForm pEventForm, BindingResult bindingResult,Model model, final RedirectAttributes redirectAttributes) {
		System.out.println("JOIN() : {}"+ pEventForm);
		EventVO i_EventVO = m_EventsService.findById(id);
		i_EventVO.setStatus(EventsService.m_Status_Joined);
		m_EventsService.update(i_EventVO);
		redirectAttributes.addFlashAttribute("msg", "Joined event sucessfully!");
		return "redirect:/screen2";
	}	
	@RequestMapping(value = "/events/{id}/Delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,@ModelAttribute("eventForm") EventForm pEventForm, BindingResult bindingResult,Model model, final RedirectAttributes redirectAttributes) {
		System.out.println("DELETE() : {}"+ pEventForm);
		EventVO i_EventVO = m_EventsService.findById(id);
		i_EventVO.setStatus(EventsService.m_Status_Deleted);
		m_EventsService.update(i_EventVO);
		redirectAttributes.addFlashAttribute("msg", "Event removed sucessfully!");
		return "redirect:/screen2";
	}		
	@RequestMapping(value = "/events/{id}/Cancel", method = RequestMethod.GET)
	public String cancel(@PathVariable("id") Long id,@ModelAttribute("eventForm") EventForm pEventForm, BindingResult bindingResult,Model model, final RedirectAttributes redirectAttributes) {
		System.out.println("CANCEL() : {}"+ pEventForm);
		EventVO i_EventVO = toEventVO(pEventForm);
		i_EventVO.setStatus(EventsService.m_Status_New);
		m_EventsService.update(i_EventVO);
		redirectAttributes.addFlashAttribute("msg", "Event is cancelled!");
		return "redirect:/screen2";
	}	
	//Screen4
	@RequestMapping(value = "/events/{id}/details", method = RequestMethod.GET)
	public String details(@PathVariable("id") Long id, Model model,final RedirectAttributes redirectAttributes) {
		System.out.println("details() : {}"+ id);
		EventVO i_EventVO = m_EventsService.findById(id);
		System.out.println(i_EventVO);
		model.addAttribute("selectedevent", i_EventVO);
		return "screen4";
	}	
	// Util functions
	private EventVO toEventVO(EventForm pEventForm) {
		EventVO i_EventVO = new EventVO();
		i_EventVO.setId(pEventForm.getId());
		i_EventVO.setName(pEventForm.getName());
		i_EventVO.setHost(pEventForm.getHost());
		i_EventVO.setStatus(pEventForm.getStatus());
		i_EventVO.setDate( Calendar.getInstance().getTime());//FORMAT TODO
		i_EventVO.setLocation(pEventForm.getLocation());
		i_EventVO.setState(pEventForm.getState());
		return i_EventVO;
	}
}