package com.finalChallenge.CreditCardSpringAPI.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
    @Id
    private int id;
    private String trans_date_trans_time;

    private float amt;
    private float trans_num;
    private float customer_id;
    private String city;
    private String state;
    private float city_population;
    private String merchant;
    private String category;
    private String first;
    private String last;
    private String gender;
    private String Job;
    private String dob;

    public Transaction(int id, String trans_date_trans_time, float amt, float trans_num, float customer_id, String city, String state, float city_population, String merchant, String category, String first, String last, String gender, String job, String dob) {
        this.id = id;
        this.trans_date_trans_time = trans_date_trans_time;
        this.amt = amt;
        this.trans_num = trans_num;
        this.customer_id = customer_id;
        this.city = city;
        this.state = state;
        this.city_population = city_population;
        this.merchant = merchant;
        this.category = category;
        this.first = first;
        this.last = last;
        this.gender = gender;
        Job = job;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrans_date_trans_time() {
        return trans_date_trans_time;
    }

    public void setTrans_date_trans_time(String trans_date_trans_time) {
        this.trans_date_trans_time = trans_date_trans_time;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public float getTrans_num() {
        return trans_num;
    }

    public void setTrans_num(float trans_num) {
        this.trans_num = trans_num;
    }

    public float getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(float customer_id) {
        this.customer_id = customer_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getCity_population() {
        return city_population;
    }

    public void setCity_population(float city_population) {
        this.city_population = city_population;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



}
