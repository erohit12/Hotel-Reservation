package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomerService {
    private static CustomerService INSTANCE;

    private CustomerService(){}

    public static CustomerService getInstance(){
        if (INSTANCE == null)
            INSTANCE = new CustomerService();

        return INSTANCE;
    }

    static Set<Customer> customers = new HashSet<>();

    public static void addCustomer(String email, String firstName, String lastName){
        customers.add(new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String email){
        for(Customer customer: customers){
            if (email.equals(customer.getEmail())) {
                return customer;
            }
        }
        return null;
    }

    public static Collection<Customer> getAllCustomer(){
        return customers;
    }

}
