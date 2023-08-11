package com.finalChallenge.CreditCardSpringAPI.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Customer {
    @Id
    private int customer_id;
    private String firstName;
    private String lastName;
    private String gender;

    private String job;
    private Date dob;

    public Customer(int customer_id, String firstName, String lastName, String gender, String job, Date dob) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
