package com.hcl.bootcamp.fs.springboot.app.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.hcl.bootcamp.fs.springboot.app.model.EventForm;
import com.hcl.bootcamp.fs.springboot.app.model.EventVO;
import com.hcl.bootcamp.fs.springboot.app.service.EventsService;

@Component
public class EventValidator implements Validator {
    @Autowired
    private EventsService m_EventsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	EventForm i_EventForm = (EventForm) o;
        System.out.println("1.Name " + i_EventForm.getName());
        System.out.println("2.Date " + i_EventForm.getDate());
        System.out.println("3.Location " + i_EventForm.getLocation());
        System.out.println("4.Location " + i_EventForm.getState());
        //System.out.println("5.Status " + i_EventForm.getStatus());
        
        //Duplicate check
        List<EventVO> i_EventVOList = m_EventsService.findByName(i_EventForm.getName());
        if ( i_EventVOList != null && i_EventVOList.size()> 0) {
            errors.rejectValue("name", "Duplicate.eventForm.eventName");
        }
        
        //Name
        if (i_EventForm.getName().length() < 6 || i_EventForm.getName().length() > 32) {
            errors.rejectValue("name", "Size.eventForm.getName");
        }
        //Date 
        //TODO
        
        //Location
        if (i_EventForm.getLocation().length() < 6 || i_EventForm.getLocation().length() > 32) {
            errors.rejectValue("location", "Size.eventForm.getLocation");
        }        
        
//        //Host NOT an input
//        if (i_EventForm.getHost().length() < 6 || i_EventForm.getHost().length() > 32) {
//            errors.rejectValue("getHost", "Size.eventForm.getHost");
//        } 
        //Status
//        if (i_EventForm.getStatus().length() < 6 || i_EventForm.getStatus().length() > 32) {
//            errors.rejectValue("getStatus", "Size.eventForm.getStatus");
//        }        

    }
}
