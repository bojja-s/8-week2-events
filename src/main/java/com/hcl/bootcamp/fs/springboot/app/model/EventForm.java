package com.hcl.bootcamp.fs.springboot.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class EventForm {

	String name;
	Date date;
	String location;
	String host;
	String status;
	

}
