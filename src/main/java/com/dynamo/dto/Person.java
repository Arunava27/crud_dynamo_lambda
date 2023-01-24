package com.dynamo.dto;

public class Person {
	    private int id;
	    private String firstName;
	    private String lastName;
	    private int age;
	    private String address;
	    
	    public Person(int id, String firstName, String lastName, int age, String address) {
	    	this.id = id;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.age = age;
	    	this.address = address;
	    }

		public int getId() {
			return id;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public int getAge() {
			return age;
		}

		public String getAddress() {
			return address;
		}
	    
	    
		
	    
	    
}
