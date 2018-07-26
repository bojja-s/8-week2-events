package com.hcl.bootcamp.fs.springboot.app.model;

import lombok.Data;

@Data
public class EventForm {

	private Long id;
	private String name;
	private String date;
	private String location;
	private String state;
	private String host;
	private String status;
	private String comment;
	

}
