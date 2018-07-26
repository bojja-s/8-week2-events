package com.hcl.bootcamp.fs.springboot.app.model;

public class NamesOnly {


		  private final String firstname, lastname;

		  NamesOnly(String firstname, String lastname) {

		    this.firstname = firstname;
		    this.lastname = lastname;
		  }

		  String getFirstname() {
		    return this.firstname;
		  }

		  String getLastname() {
		    return this.lastname;
		  }

}
