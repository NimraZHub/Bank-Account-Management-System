package entities;

import java.io.Serializable;

public class Customer implements Serializable{
    private String customerId;
    private String name;
    private String contactNumber;
    private String email;

    public Customer(String customerId, String name, String contactNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public String toString(){
        return " Customer ID: "  + customerId + " , Name: " + name + " , Contact: " + contactNumber + " , Email: " + email; 
    }
    
}
