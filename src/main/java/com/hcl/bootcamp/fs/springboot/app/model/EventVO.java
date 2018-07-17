package com.hcl.bootcamp.fs.springboot.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "events")
public class EventVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	Long id;
	String name;
	Date Date;
	String location;
	String state;
	String host;
	String status;
	transient String[] actions;
	
	@ManyToOne
	@JoinColumn(name = "host" ,referencedColumnName="userName", nullable = false , insertable = false, updatable = false)
	private User user;
	   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String[] getActions() {
		return actions;
	}
	public void setActions(String[] actions) {
		this.actions = actions;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "EventVO [id=" + id + ", name=" + name + ", Date=" + Date + ", location=" + location + ", state=" + state
				+ ", host=" + host + ", status=" + status + ", user=" + user + "]";
	}
	
}
